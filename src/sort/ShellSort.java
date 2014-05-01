package sort;

import java.util.ArrayList;
import java.util.Collections;

public class ShellSort {

	class MyArrayList extends ArrayList<Integer> {

		private static final long serialVersionUID = 2191258924393802375L;

		public MyArrayList(int maxsize) {
			super(maxsize);
		}

		public String toString() {
			return super.toString();
		}

		public void shellSort() {
			int count = 0;
			int inner, outer;
			int temp;

			int h = 1;
			while (h <= this.size() / 3) {
				h = h * 3 + 1;
			}

			while (h > 0) {
				for (outer = h; outer < this.size(); outer++) {
					temp = this.get(outer);
					inner = outer;

					while (inner > h - 1 && this.get(inner - h) >= temp) {
						this.set(inner, this.get(inner - h));
						inner -= h;
						count++;
					}

					this.set(inner, temp);
				}
				h = (h - 1) / 3;
			}
			
			System.out.println(count);
		}
	}

	public static void main(String[] args) {

		int maxsize = 10;
		int[] list = new int[maxsize];
		ShellSort s = new ShellSort();
		MyArrayList myArrayLists = s.new MyArrayList(maxsize);

		for (int i = 0; i < maxsize; i++) {
			list[i] = (int) (Math.random() * 99);
			System.out.print(list[i] + "  ");
			myArrayLists.add(list[i]);
		}
		System.out.println();
		myArrayLists.shellSort();
		System.out.println(myArrayLists);
	}
}
