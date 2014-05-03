package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int maxSize = 99;
		ArrayMer arr = new ArrayMer(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 999));
		}

		arr.display();
		System.out.println();
		arr.mergeSort();
		arr.display();
	}

	private static void display(int[] arrayC) {

		for (int i = 0; i < arrayC.length; i++) {
			System.out.print(arrayC[i] + " ");
		}
	}

	private static void merge(int[] arrayA, int[] arrayB, int[] arrayC) {

	}

}

class ArrayMer {
	private long[] a;
	private int nElems;

	public ArrayMer(int max) {
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

	public void mergeSort() {
		long[] workSpace = new long[nElems];
		recMergeSort(workSpace, 0, nElems - 1);
	}

	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
		if (lowerBound == upperBound)
			return;
		else {
			int mid = (lowerBound + upperBound) / 2;
			recMergeSort(workSpace, lowerBound, mid);
			recMergeSort(workSpace, mid + 1, upperBound);
			merge(workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0;
		int lowBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowBound + 1;
		while (lowPtr <= mid && highPtr <= upperBound) {
			if (a[lowPtr] < a[highPtr]) {
				workSpace[j++] = a[lowPtr++];
			} else {
				workSpace[j++] = a[highPtr++];
			}
		}

		while (lowPtr <= mid) {
			workSpace[j++] = a[lowPtr++];
		}

		while (highPtr <= upperBound) {
			workSpace[j++] = a[highPtr++];
		}

		for (j = 0; j < n; j++) {
			a[lowBound + j] = workSpace[j];
		}
	}
}
