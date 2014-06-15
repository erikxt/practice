package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class SelectSocketsThreadPool extends SelectSockets {
	
	private static final int MAX_THREAD = 5;
	private ThreadPool pool = new ThreadPool(MAX_THREAD);
	
	public static void main(String[] argv) throws Exception {
		new SelectSocketsThreadPool();
	}
	
	protected void readDataFromSocket(SelectionKey key) throws Exception {
		WorkerThread worker = pool.getWorker();
		
		if (worker == null) {
			return;
		}
		
		worker.serviceChannel(key);
	}

	private class ThreadPool {
		List idle = new LinkedList();

		ThreadPool(int poolSize) {
			for (int i = 0; i < poolSize; i++) {
				WorkerThread thread = new WorkerThread(this);
			}
		}

		public WorkerThread getWorker() {
			WorkerThread worker = null;
			synchronized (idle) {
				if (idle.size() > 0) {
					worker = (WorkerThread) idle.remove(0);
				}
			}

			return worker;
		}

		public void returnWorker(WorkerThread worker) {
			synchronized (idle) {
				idle.add(worker);
			}
		}
	}

	private class WorkerThread extends Thread {
		private ByteBuffer buffer = ByteBuffer.allocate(1024);
		private ThreadPool pool;
		private SelectionKey key;

		WorkerThread(ThreadPool pool) {
			this.pool = pool;
		}

		public/* synchronized */void run() {
			System.out.println(this.getName() + " is ready");
			while (true) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					this.interrupted();
				}

				if (key == null) {
					continue;
				}

				System.out.println(this.getName() + " has been awakened");
				try {
					drainChannel(key);
				} catch (Exception e) {
//					e.printStackTrace();
					System.out.println("Caught '" + e + "' closing channel");
					
					try {
						key.channel().close();
					}
					catch (IOException ex) {
						ex.printStackTrace();
					}
					key.selector().wakeup();
				}
				key = null;
				this.pool.returnWorker(this);
			}
		}

		synchronized void serviceChannel(SelectionKey key) throws Exception {
			this.key = key;
			
			key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));

			this.notify();
		}

		/**
		 * The actual code which drains the channel associated with the given *
		 * key. This method assumes the key has been modified prior to *
		 * invocation to turn off selection interest in OP_READ. When this *
		 * method completes it re-enables OP_READ and calls wakeup( ) on the *
		 * selector so the selector will resume watching this channel.
		 */
		void drainChannel(SelectionKey key) throws Exception {
			SocketChannel channel = (SocketChannel) key.channel();
			int count;

			buffer.clear();

			while ((count = channel.read(buffer)) > 0) {
				buffer.flip();

				while (buffer.hasRemaining()) {
					channel.write(buffer);
				}

				buffer.clear();
			}

			if (count < 0) {
				channel.close();
				return;
			}

			key.interestOps(key.interestOps() | SelectionKey.OP_READ);
			key.selector().wakeup();
		}
	}
}
