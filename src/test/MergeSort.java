package test;

public class MergeSort {

	public static void main (String[] args)
	{
		int[] arrayA = {1, 3, 5, 7, 9};
		int[] arrayB = {2, 4, 5, 8};
		int[] arrayC = new int[arrayA.length + arrayB.length];
		
		merge(arrayA, arrayB, arrayC);
		display(arrayC);
	}

	private static void display(int[] arrayC) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrayC.length; i++)
		{
			System.out.print(arrayC[i] + " ");
		}
	}

	private static void merge(int[] arrayA, int[] arrayB, int[] arrayC) {
		// TODO Auto-generated method stub
		int aDex = 0, bDex = 0, cDex = 0;
		while (aDex < arrayA.length && bDex < arrayB.length)
		{
			if (arrayA[aDex] < arrayB[bDex])
			{
				arrayC[cDex++] = arrayA[aDex++];
			}
			else
			{
				arrayC[cDex++] = arrayB[bDex++];
			}
		}
		
		while (aDex < arrayA.length)
		{
			arrayC[cDex++] = arrayA[aDex++];
		}
		
		while (bDex < arrayB.length)
		{
			arrayC[cDex++] = arrayB[bDex++];
		}
	}	
	
	private static void recMergeSort(int[] workspace, int lowerBound, int upperBound)
	{
		if (lowerBound == upperBound)
			return;
		else
		{
			int mid = (lowerBound + upperBound) / 2;
			
			recMergeSort(workspace, lowerBound, mid);
			
			recMergeSort(workspace, mid + 1, upperBound);
			
		}
	}

	private static void merge(int[] workspace, int lowerBound, int i,
			int upperBound) {
		// TODO Auto-generated method stub
		
	}
}
