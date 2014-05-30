package multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	public static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static List<String> list = new ArrayList<String>();
	
	static {
		list.add("hello");
		list.add("world");
	}
	
	public static void f1()
	{
		System.out.println("enter f1");
		try {
			lock.readLock().tryLock(6000, TimeUnit.SECONDS);
			System.out.println("add read lock");
			System.out.println(list.get(0));
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();

		}
		finally 
		{
			lock.readLock().unlock();
			System.out.println("leave f1");
		}
	}
	
	public static void f2() throws InterruptedException
	{
		System.out.println("enter f2");
		try {
			lock.readLock().tryLock(6000, TimeUnit.SECONDS);
			System.out.println("add read lock");
			System.out.println(list.get(1));
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			lock.readLock().unlock();
			System.out.println("leave f2");
		}
	}
	
	public static void f3() throws InterruptedException
	{
		Thread.sleep(1);
		System.out.println("enter f3");
		try {
			lock.writeLock().lock();
			System.out.println("add write lock");
			list.add("hi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			lock.writeLock().unlock();
			System.out.println("leave f3");
		}
	}
	
	public static void main (String[] args)
	{
		System.out.println("static synchronizd test");
		Thread t1 = new Thread() { 
			public void run() { 
				f1();
			}
		};
		Thread t2 = new Thread() { 
			public void run() { 
				try {
					f2();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		};
		Thread t3 = new Thread() { 
			public void run() { 
				try {
					f3();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
		t3.start();
	}
}
