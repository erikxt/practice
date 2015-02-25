package sort;

public class QuickSort {

	public static void main(String[] args) {
		int maxSize = 17;
		ArrayPar arr = new ArrayPar(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 199));
		}

		arr.display();
		System.out.println();
		arr.quickSort();
		arr.display();
	}
}

class ArrayPar {
	private long[] a;
	private int nElems;

	public ArrayPar(int max) {
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

	public void quickSort() {
		recParSort(0, nElems - 1);
	}

	private void recParSort(int left, int right) {
		if (right - left <= 0)
			return;
		else {
			long pivot = a[right];
			int partition = partitionIt(left, right, pivot);
			recParSort(left, partition - 1);
			recParSort(partition + 1, right);
			// merge(workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private int partitionIt(int left, int right, long pivot) {
		int leftPrt = left - 1;
		int rightPtr = right;
		while (true) {
			while (a[++leftPrt] < pivot)
				;
			while ( a[--rightPtr] > pivot)
				System.out.print(a[rightPtr] + "\t");
            System.out.println();
			if (leftPrt >= rightPtr) {
				break;
			} else {
				swap(leftPrt, rightPtr);
			}
		}
		swap(leftPrt, right);

		return leftPrt;
	}

}
