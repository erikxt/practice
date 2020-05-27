package aop;

public class HelloWorldAspect {

	public void beforeAdvice() {
		System.out.println("=============before================");
	}
	
	public void afterAdvice() {
		System.out.println("=============after=================");
	}
}
