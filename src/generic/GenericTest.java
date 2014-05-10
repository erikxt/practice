package generic;

import java.util.*;

public class GenericTest {

	public static void main(String[] args) {
		List<? super Apple> flist = new ArrayList<Fruit>();
		flist.add(new Apple());
	}

	class SelfBounded<T extends SelfBounded<T>> {

	}
}
