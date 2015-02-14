package aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AopTest {

	@Test
	public void testHelloWorld() {
		ApplicationContext ctx =
				   new ClassPathXmlApplicationContext("classpath:aop/AopConfig.xml");
		IHelloWorldService helloworldService = 
				ctx.getBean("helloWorldService", IHelloWorldService.class);
		helloworldService.sayHello();
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
