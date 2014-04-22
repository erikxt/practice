package sort;

import java.io.*;

public class QuickSort {

	private long[] theArray;
	
	public void quickSort(int left, int right)
	{
		if (left <= right)
		{
			return;
		}
		else
		{
//			long pivot = theArray[right];
			int partition = partitionIt(left, right);
			quickSort(left, partition - 1);
			quickSort(partition + 1, right);
		}
	}
	
	public void swap(int dex1, int dex2)
	{
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}
	
	public int partitionIt(int left, int right)
	{
		
		return 0;	
	}
	
	public static void main (String args[])
	{
		
	}
}
