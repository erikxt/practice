package singleton;

public class InnerClassSingleton {

	private static class SingletonHolder {

		private static InnerClassSingleton instance = new InnerClassSingleton();

	}

	private InnerClassSingleton() {

	}

	// 按需获取 由JVM隐式得进行同步操作
	public static InnerClassSingleton getInstance() {
		return SingletonHolder.instance;
	}
}
