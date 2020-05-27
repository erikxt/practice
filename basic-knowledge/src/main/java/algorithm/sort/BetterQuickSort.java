package algorithm.sort;

public class BetterQuickSort {

    public static void main(String[] args) {
        int maxSize = 30;
        ArrayBetter arr = new ArrayBetter(maxSize);

        for (int i = 0; i < maxSize; i++) {
            arr.insert((int) (Math.random() * 299));
        }

        arr.display();
        System.out.println();
        arr.quickSort();
        arr.display();
    }
}

class ArrayBetter {
    long[] a;
    int nElems;

    public ArrayBetter(int max) {
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

    public long medianOf3(int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid])
            swap(left, mid);
        if (a[left] > a[right])
            swap(left, right);
        if (a[mid] > a[right])
            swap(mid, right);
        swap(mid,right - 1);
        return a[right - 1];
    }

    private int partitionIt(int left, int right, long pivot) {
        int leftPrt = left;
        int rightPtr = right - 1;
        while (true) {
            while (a[++leftPrt] < pivot)
                ;
            while (a[--rightPtr] > pivot)
                ;
            if (leftPrt >= rightPtr) {
                break;
            } else {
                swap(leftPrt, rightPtr);
            }
        }
        swap(leftPrt, right - 1);
        return leftPrt;
    }

    public void quickSort() {
        recParSort(0, nElems - 1);
    }

    private void recParSort(int left, int right) {
        if (right - left + 1 <= 3)
            manualSort(left, right);
        else {
            long pivot = medianOf3(left, right);
            int partition = partitionIt(left, right, pivot);
            recParSort(left, partition - 1);
            recParSort(partition + 1, right);
            // merge(workSpace, lefterBound, mid + 1, upperBound);
        }
    }

    public void manualSort(int left, int right) {
        int size =  right - left + 1;
        if (size == 1)
            return;
        if (size == 2) {
            if (a[left] > a[right])
                swap(left, right);
        }
        else {
            if (a[left] > a[right - 1])
                swap(left, right - 1);
            if (a[left] > a[right])
                swap(left, right);
            if (a[right - 1] > a[right])
                swap(right - 1, right);
        }

    }
}