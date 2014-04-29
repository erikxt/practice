package algorithm;

import java.util.List;

public class MatrixOfYoung {
	
	private int row;
	
	private int column;
	
	private int value;
	
	public static void main(String[] args)
	{
		 int matrix[][] = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 },  
	                { 6, 8, 11, 15 } }; 
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
