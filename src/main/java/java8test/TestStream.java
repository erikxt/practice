package java8test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStream {
	
	static class Person {
		
		public Person(int age) {
			super();
			this.age = age;
		}

		int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
	
	static class Adult extends Person {
		
		public Adult(Person p) {
			super(p.getAge());
		}
	}
	
	public static List<Person> persons = new ArrayList<Person>();
	
	private static long countPrimes(int max) {
	    return IntStream.range(1, max).parallel().filter(TestStream::isPrime).count();
	}

	private static boolean isPrime(long n) {
	    return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(divisor -> n % divisor == 0);
	}
	
	public static void main(String[] args) {
		
		persons = Arrays.asList(new Person(19), new Person(17), new Person(20));
		List<Adult> adultList = (List<Adult>) persons
	            .stream()
	            .filter(p -> p.getAge() > 18)
	            .map(person -> new Adult(person))
	            .collect(Collectors.toCollection(ArrayList::new));
		adultList.forEach(a->System.out.println(a.getAge()));
		
		long t0 = System.nanoTime();
		 
		//初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法
		 
		IntStream.range(0, 1_000_000).filter(p -> p % 2==0).toArray();
		 
		long t1 = System.nanoTime();
		 
		//和上面功能一样，这里是用并行流来计算
		 
		IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2==0).toArray();
		 
		long t2 = System.nanoTime();
		 
		//我本机的结果是serial: 0.02s, parallel 0.09s???
		//
		 
		System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
		
		System.out.println(countPrimes(6));
	}	
}
