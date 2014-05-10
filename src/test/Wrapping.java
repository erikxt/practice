package test;

import java.util.HashMap;
import java.util.Map;

public class Wrapping {
	volatile Map a = new HashMap();
	private int i;
	public Wrapping(int x) { i = x; }
	public int value() { return i; }
}
