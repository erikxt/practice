package test;

class ParentClass {
	public static char staticField = 'a';
	public final char finalField;
	public static final char staticFinalField;
	
	{
		finalField = 'b';
		
	}
	
	static{
		staticFinalField = 'c';
	}
	
	
}

public class SubClass extends ParentClass {
	public static char staticField = 'd';
	public final char finalField = 'e';
	public static final char staticFinalField = 'f';
	public static void main(String[] args) {
		SubClass subClass = new SubClass();
		System.out.println(SubClass.staticField);
		// 注意，这里的subClass变量，不是SubClass类
		System.out.println(subClass.finalField);
		System.out.println(SubClass.staticFinalField);
		
	}
}
