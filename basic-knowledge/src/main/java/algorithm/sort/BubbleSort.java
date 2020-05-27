package algorithm.sort;

public class BubbleSort {

	public static void main(String[] args) {
		int maxSize = 20;
		ArrayBub arr = new ArrayBub(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 99));
		}

		arr.display();
		arr.bubbleSort();
		arr.display();
	}
}

class ArrayBub {
	private long[] a;
	private int nElems;

	public ArrayBub(int max) {
		a = new long[max];
		nElems = 0;
	}

	public void insert(long value) {
		a[nElems] = value;
		nElems++;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");
	}

	public void swap(int one, int two) {
		long temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}

	public void bubbleSort() {
		int out, in;

		for (out = 0; out < nElems - 1; out++) {
			for (in = out + 1; in < nElems; in++) {
				if (a[in] < a[out])
					swap(in, out);
			}
		}

	}
}
