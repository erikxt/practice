package test;

public final class FinallyTest {
	// 重写finalize()方法
	@Override
	protected void finalize() throws Throwable {
		System.out.println("执行了finalize()方法");
	}
	public static void main(String[] args) {
		FinallyTest ft = new FinallyTest();
		ft = null;
		System.gc();
	}
}