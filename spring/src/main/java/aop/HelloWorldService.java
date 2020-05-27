package aop;

public class HelloWorldService implements IHelloWorldService {

	@Override
	public void sayHello() {
		System.out.println("=============CRUD================");
	}

}
