package ca.sheridancollege.ca.beans;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatrixCalculation {

	// Method to add two matrices of user input values.
	public double[][] addMatrices(double[][] matrixOne, double[][] matrixTwo, int m, int n) {
		double[][] matrixAdd = new double[m][n];
		for (int i = 0; i < matrixAdd.length; i++) {
			for (int j = 0; j < matrixAdd[i].length; j++) {
				matrixAdd[i][j] = matrixOne[i][j] + matrixTwo[i][j];
			}
		}
		return matrixAdd;
	}

	// To square any one of the matrix.
	public double[][] squareMatrices(double[][] matrixOne, int m, int n) {
		double[][] matrixSquare = new double[m][n];
		for (int i = 0; i < matrixSquare.length; i++) {
			for (int j = 0; j < matrixSquare[i].length; j++) {
				matrixSquare[i][j] = matrixOne[i][j] * matrixOne[i][j];
			}
		}
		return matrixSquare;
	}

	// method to read a file and build matrix from it.
	public double[][] buildMatrix(String fname) throws IOException {
		int rows = 0;
		int columns = 0;

		Scanner sc = new Scanner(new File(fname));
		while (sc.hasNextLine()) {
			++rows;
			columns = 0;
			Scanner colReader = new Scanner(sc.nextLine());
			while (colReader.hasNextDouble()) {
				colReader.nextDouble();
				++columns;
			}
			colReader.close();
		}
		double[][] array = new double[rows][columns];
		sc.close();

		sc = new Scanner(new File(fname));
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				if (sc.hasNextDouble()) {
					array[i][j] = sc.nextDouble();
				}
			}
		}
		return array;
	}

	// Method for printing arrays
	public String printArray(double[][] matrix) {
		String s = "";
		s += "\n";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				s += Double.toString(matrix[i][j]) + "   ";
			}
			s += "\n";
		}
		return s;
	}

}
