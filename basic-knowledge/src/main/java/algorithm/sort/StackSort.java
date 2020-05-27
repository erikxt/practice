package algorithm.sort;

public class StackSort {

	public static void main(String[] args) {
		int maxSize = 8;
		ArrayStack arr = new ArrayStack(maxSize);

		for (int i = 0; i < maxSize; i++) {
			arr.insert((int) (Math.random() * 99));
		}

		arr.display();
		System.out.println();
		arr.stackSort();
		arr.display();
	}
}

class ArrayStack {
	private long[] a;
	private int nElems;

	public ArrayStack(int max) {
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

	public void stackSort() {
		long temp = 0;

		for (int i = nElems / 2; i > 0; i--) {
			adjust(a, i - 1, nElems);
			System.out.println("建立大根堆");
			display();
		}

		for (int i = nElems - 1; i > 0; i--) { // 这是堆排序的具体算法，思想是每次取出堆的最顶层根节点，即数组下标为0，然后与最后一个节点即i+1交换，这样对于大根堆而言，最大值总是在后面。。循环过后就能排序了。。
			temp = a[i]; // 取出最后一个元素
			a[i] = a[0]; // 取出第一个元素，即顶层根节点
			a[0] = temp; // 交换位置

			adjust(a, 0, i); // 调整堆
			System.out.println("重建大根堆");
			display();
		}
	}

	private void adjust(long[] a, int i, int n) {
		int j = 0;
		long temp = 0;

		temp = a[i]; // 取出根节点
		j = 2 * i + i; // 左孩子节点

		while (j <= n - 1) {
			if (j < n - 1 && a[j] < a[j + 1]) { // 比较左右孩子，取出较大的孩子
				j++;
			}
			if (temp >= a[j]) { // 如果根节点大于孩子节点则退出循环，不用调整
				break;
			}
			a[(j - 1) / 2] = a[j]; // 较大的孩子节点值赋值给根节点
			j = j * 2 + 1; // 继续寻找左孩子
		}
		a[(j - 1) / 2] = temp; // 将根节点赋值给最后一个空出来的节点
	}
}
