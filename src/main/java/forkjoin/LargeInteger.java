package forkjoin;

import java.util.Random;

//This class defines a list which will contain large number of integers.
public class LargeInteger {
	private final int[] list = new int[2000000];

	public LargeInteger() {
		Random generator = new Random(19580427);
		for (int i = 0; i < list.length; i++) {
			list[i] = generator.nextInt(500000);
		}
	}

	public int[] getList() {
		return list;
	}
}