package sort;

public class ShellSort {
	
	public static void main(String[] args) {

		int maxsize = 10;
		ArrayShl myArrayLists = new ArrayShl(maxsize);

		for (int i = 0; i < maxsize; i++) {
			myArrayLists.insert((long) (Math.random() * 99));
		}
		System.out.println();
		myArrayLists.shellSort();
		myArrayLists.display();
	}
}

class ArrayShl {
	private long[] a;
	private int nElems;

	public ArrayShl(int max) {
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

	public void shellSort() {
		int in, out;

		int h = 1;
		while (h <= nElems / 3) {
			h = h * 3 + 1;
		}
		
		while (h > 0) {
			for (out = h; out < nElems; out++) {
				long temp = a[out];
				in = out;
				while (in > h - 1 && a[in - h] >= temp) {
					a[in] = a[in - h];
					in-=h;
				}
				a[in] = temp;
			}
			h = (h - 1) / 3;
		}
		
	}
}