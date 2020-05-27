package java8test;

interface A {
	default void hello() {
		System.out.println("hello world from a");
	}
}

interface B extends A {
	default void hello() {
		System.out.println("hello world from b");
	}
}

public class TestDefaultInterface implements A{
	
	@Override
	public void hello() {
		A.super.hello();
		A.super.hello();
	}
	
	public static void main(String[] args) {
		new TestDefaultInterface().hello();
	}
}
