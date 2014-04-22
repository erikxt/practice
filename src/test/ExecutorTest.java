package test;

import java.util.concurrent.*;

public class ExecutorTest {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future fr = es.submit(new RunnableTest());// 提交任务
		Future fc = es.submit(new CallableTest());// 提交任务
		// 取得返回值并输出
		System.out.println((String) fc.get());
		// 检查任务是否执行完毕
		if (fr.isDone()) {
			System.out.println("执行完毕-RunnableTest.run()");
		} else {
			System.out.println("未执行完-RunnableTest.run()");
		}
		// 检查任务是否执行完毕
		if (fc.isDone()) {
			System.out.println("执行完毕-CallableTest.run()");
		} else {
			System.out.println("未执行完-CallableTest.run()");
		}
		// 停止线程池服务
		es.shutdown();
	}
}

class RunnableTest implements Runnable {
	public void run() {
		System.out.println("已经执行-RunnableTest.run()");
	}
}

class CallableTest implements Callable {
	public Object call() {
		System.out.println("已经执行-CallableTest.call()");
		return "返回值-CallableTest.call()";
	}
}