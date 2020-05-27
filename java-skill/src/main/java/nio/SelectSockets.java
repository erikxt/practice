package nio;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.SelectableChannel;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.util.Iterator;

public class SelectSockets {
	public static int PORT_NUMBER = 1234;

	public static void main(String[] argv) throws Exception {
		new SelectSockets().go(argv);
	}

	public void go(String[] argv) throws Exception {
		int port = PORT_NUMBER;

		if (argv.length > 0) {
			port = Integer.parseInt(argv[0]);
		}

		System.out.println("Listening on ports " + port);

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverChannel.socket();
		Selector selector = Selector.open();

		serverSocket.bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int n = selector.select();

			if (n == 0) {
				continue;
			}

			Iterator<?> it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key
							.channel();
					SocketChannel channel = server.accept();
					registerChannel(selector, channel, SelectionKey.OP_READ);

					// sayHello(channel);
				}

				if (key.isReadable()) {
					readDataFromSocket(key);
				}
				it.remove();
			}
		}
	}

	private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

	protected void readDataFromSocket(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;
		buffer.clear();
		
		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip();
			
			while (buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}
			
			buffer.clear();
		}
		
		if (count < 0) {
			socketChannel.close();
		}
	}

	protected void registerChannel(Selector selector,
			SelectableChannel channel, int ops) throws Exception {
		if (channel == null) { 
			return; // could happen 
		}
		// Set the new channel nonblocking 
		channel.configureBlocking(false); // Register it with the selector 
		channel.register(selector, ops);
	}
}
