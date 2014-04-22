package test;

import java.util.ArrayList;
import java.util.List;

public class SuperTest {
	public static void addList(List<? super B> list)
	{	
		list.add(new D());
		A d = (A) list.get(0);   //编译通过
	}
	
	public static <B> void get(List<? super B> list)  
	{  
	    list.get(0);  
	} 
	
	public static <B> void set(List<? super B> list, B b)  
	{  
	    list.add(b);  
	} 
	
	public static void main (String args[])
	{
		List<? super B> list = new ArrayList<A>();
		addList(list);

		List<? extends A> list1 = new ArrayList<A>();  
		List<? extends A> list2 = new ArrayList<B>();  
		List<? super B> list3 = new ArrayList<B>();  
		List<? super B> list4 = new ArrayList<A>();  
	}
}



class A{}
class B extends A{}
class C extends A{}
class D extends B{}
class E extends C{}