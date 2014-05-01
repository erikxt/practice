package singleton;

public class LazySingletonTest {

	private static LazySingletonTest instance = null;

	private LazySingletonTest() {

	}

	public static synchronized LazySingletonTest getInstance() {
		if (instance == null) {
			instance = new LazySingletonTest();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		LazySingletonTest.getInstance();
	}
}
