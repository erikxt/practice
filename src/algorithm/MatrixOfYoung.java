package algorithm;

import java.util.List;

public class MatrixOfYoung {

	private int row;

	private int column;

	private int value;

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 },
				{ 6, 8, 11, 15 } };

		final int rowNum = matrix.length;
		final int colNum = matrix[0].length;

		int pos;
		int i = 0, j = colNum - 1;

		int value = 11;
		while (i < rowNum && j >= 0) {
			pos = matrix[i][j];
			if (value < pos) {
				j--;
				continue;
			} else if (value > pos) {
				i++;
				continue;
			} else {
				break;
			}
		}
		System.out.println(i + 1);
		System.out.println(j + 1);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
