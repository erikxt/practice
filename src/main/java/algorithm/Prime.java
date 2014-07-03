package algorithm;

import java.util.Scanner;

public class Prime {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] result = new int[n];
		if (n < 2) {
			System.out.println("invalid number");
			return;
		}
		for (int i = 0; i < n; i++) {
			result[i] = i + 1;
		}
		int q = (int) Math.sqrt(n);
		System.out.println(q);
		for (int i = 2; i <= q; i++) {
			int j = 2 * i;
			while (j <= n) {
				result[j - 1] = 0;
				j = j + i;
			}
		}
		System.out.println("end");
		int row = 1;
		for (int i = 0; i < n; i++) {
			if (result[i] == 0)
				continue;
			System.out.printf("%5d%s", result[i] , "  ");
			if (row % 100 == 0) {
				System.out.println("");
				row = 1;
			}
			else
			{
				row++;
			}
		}
	}
}
