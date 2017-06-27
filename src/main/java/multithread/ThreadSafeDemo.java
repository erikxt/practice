package multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeDemo {
	public static int demo(final List<String> list, final int testCount)
			throws InterruptedException {
		long startTime = System.currentTimeMillis();
		ThreadGroup group = new ThreadGroup(list.getClass().getName() + "@"
				+ list.hashCode());
		System.out.println(group);
		final Random rand = new Random();

		Runnable listAppender = new Runnable() { // 这里面实现了Runnable接口类，覆盖了它的run方法
			public void run() {
				try {
					Thread.sleep(rand.nextInt(2));
				} catch (InterruptedException e) {
					return;
				}
				list.add("0");
			}
		};

		for (int i = 0; i < testCount; i++) {
			new Thread(group, listAppender, "InsertList-" + i).start();
		}

		while (group.activeCount() > 0) {
			Thread.sleep(10);
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
		return list.size();
	}

	public static void main(String[] args) throws InterruptedException {
		List<String> unsafeList = new ArrayList<String>();
		List<String> safeList = new CopyOnWriteArrayList<String>(); // 也可以换成new
																		// CopyToWriteArrayList
		final int N = 100000;
		for (int i = 0; i < 5; i++) {
			unsafeList.clear();
			safeList.clear();
			int unsafeSize = demo(unsafeList, N);
			int safeSize = demo(safeList, N);
			System.out.println("unsafe/safe: " + unsafeSize + "/" + safeSize);
		}
	}
}
