package multithread;

public class SynchronizedTest {

	public static synchronized void f1() throws InterruptedException
	{
		System.out.println("enter f1");
		Thread.sleep(5000);
		System.out.println("leave f1");
	}
	
	public static synchronized void f2() throws InterruptedException
	{
		System.out.println("enter f2");
		Thread.sleep(5000);
		System.out.println("leave f2");
	}
	
	public synchronized void f3() throws InterruptedException
	{
		System.out.println("enter f3");
		Thread.sleep(5000);
		System.out.println("leave f3");
	}
	
	public synchronized void f4() throws InterruptedException
	{
		System.out.println("enter f4");
		Thread.sleep(5000);
		System.out.println("leave f4");
	}
	
	public static void main (String[] args)
	{
		System.out.println("static synchronizd test");
		Thread t1 = new Thread() { 
			public void run() { 
				try {
					SynchronizedTest.f1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread() { 
			public void run() { 
				try {
					SynchronizedTest.f2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
		
		System.out.println("non-static synchronized test");
		final SynchronizedTest s1 = new SynchronizedTest();
		Thread t3 = new Thread() { 
			public void run() { 
				try {
					s1.f3();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t4 = new Thread() { 
			public void run() { 
				try {
					s1.f4();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t3.start();
		t4.start();
	}
}
