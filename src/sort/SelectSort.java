package sort;

public class SelectSort {

	public static void main(String[] args) {
		int maxSize = 20;
		ArraySel arr = new ArraySel(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 99));
		}

		arr.display();
		System.out.println();
		arr.selSort();
		arr.display();
	}
}

class ArraySel {
	private long[] a;
	private int nElems;

	public ArraySel(int max) {
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

	public void selSort() {
		int out, in, min;

		for (out = 0; out < nElems - 1; out++) {
			min = out;
			for (in = out + 1; in < nElems; in++) {
				if (a[in] < a[out])
					min = in;
			}
			swap(min, out);
		}

	}
}
