package sort;

/**
 * Created by erik on 2016/5/19.
 */
public class HeapSort {

    public static void main(String[] args) {
        int maxSize = 10;
        ArrayHeap arr = new ArrayHeap(maxSize);

        for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 99));
        }

        arr.display();
        System.out.println();
        arr.heapSort();
        arr.display();
    }
}

class ArrayHeap {
    private long[] a;
    private int nElems;

    public ArrayHeap(int max) {
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

    public void swap(int i, int j) {
        long temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void heapSort() {
        makeMinHeap();
    }

    public void makeMinHeap() {
        for (int i = (int) Math.floor((nElems - 1) / 2); i >=0; i--) {
            minifyHeap(i);
        }
    }

    public void minifyHeap(int index) {
        int min, left, right;
        while (true) {
            min = index;
            left = 2 * min;
            right = 2 * min + 1;

            if(left < nElems && a[left] < a[min])
                min = left;

            if(right < nElems && a[right] < a[min])
                min = right;

            if(min != index) {
                swap(min, index);
                index = min;
            }
            else {
                break;
            }
        }
    }
}
