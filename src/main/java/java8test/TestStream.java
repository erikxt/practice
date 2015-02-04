package java8test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public static void main(String[] args) {
		
		Person p1 = new Person(19);
		Person p2 = new Person(17);
		persons.add(p1);
		persons.add(p2);
		List<Adult> adultList = (List<Adult>) persons
	            .stream()
	            .filter(p -> p.getAge() > 18)
	            .map(person -> new Adult(person))
	            .collect(Collectors.toCollection(ArrayList::new));
		adultList.forEach(a->System.out.println(a.getAge()));
	}	
}
