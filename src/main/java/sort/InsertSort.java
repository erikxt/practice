package sort;

public class InsertSort {

	public static void main(String[] args) {
		int maxSize = 8;
		ArrayIns arr = new ArrayIns(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 99));
		}

		arr.display();
		System.out.println();
		arr.insSort();
		arr.display();
	}
}

class ArrayIns {
	private long[] a;
	private int nElems;

	public ArrayIns(int max) {
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

	public void insSort() {
		int in, out;

		for (out = 1; out < nElems; out++) {
			long temp = a[out];
			in = out;
			while (in > 0 && a[in - 1] >= temp) {
				a[in] = a[in - 1];
				--in;
			}
			a[in] = temp;
		}

	}
}
