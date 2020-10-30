package ca.sheridancollege.ca.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.ca.beans.Matrix;
import ca.sheridancollege.ca.beans.MatrixCalculation;

@Controller
public class HomeController {

	@GetMapping("/") // to access localhost:8080
	public String asktheuser(Model model) {
		model.addAttribute("matrix", new Matrix());
		return "Index.html";
	}

	@GetMapping("/generate") // to access localhost:8080/generate
	public String generateMatrices(@RequestParam int m, @RequestParam int n, @RequestParam String option, Model model)
			throws IOException {
		Matrix matrix = new Matrix();
		MatrixCalculation mo = new MatrixCalculation();

		double[][] matrixOne = new double[m][n];
		double[][] matrixTwo = new double[m][n];
		double[][] matrixAdd = new double[m][n];
		double[][] matrixSquare = new double[m][n];
		Random rand = new Random();
		String s = "";

		// For generating 1st Matrix
		for (int i = 0; i < matrixOne.length; i++) {
			for (int j = 0; j < matrixOne[i].length; j++) {
				matrixOne[i][j] = rand.nextInt(9) + 1;
			}
		}

		// For generating 2nd Matrix
		for (int i = 0; i < matrixTwo.length; i++) {
			for (int j = 0; j < matrixTwo[i].length; j++) {
				matrixTwo[i][j] = rand.nextInt(9) + 1;
			}
		}

		// Writing both matrices into a file.
		try (FileWriter writer = new FileWriter("Matrices.txt"); BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write("Matrix One \n");
			// print 1st matrix
			for (int i = 0; i < matrixOne.length; i++) {
				for (int j = 0; j < matrixOne[i].length; j++) {
					bw.write(Double.toString(matrixOne[i][j]) + "  ");
				}
				bw.newLine();
			}
			bw.write("\n Matrix two \n");

			// print 2nd matrix
			for (int i = 0; i < matrixTwo.length; i++) {
				for (int j = 0; j < matrixTwo[i].length; j++) {
					bw.write(Double.toString(matrixTwo[i][j]) + "  ");
				}
				bw.newLine();
			}

		} catch (IOException e) {
			e.getMessage();
		}

		// ADDING MATRICES if option selected is one.
		if (option.equals("one")) {
			matrixAdd = mo.addMatrices(matrixOne, matrixTwo, m, n);
			s = mo.printArray(matrixAdd);
		}

		// SQUARING MATRICES if option selected is two.
		if (option.equals("two")) {
			matrixSquare = mo.squareMatrices(matrixTwo, m, n);
			s = mo.printArray(matrixSquare);
		}

		// Printing arrays from files and doing operations if option is three.
		if (option.equals("three")) {
			String s1 = "";
			String s2 = "";
			String sAdd = "";
			String sSq = "";

			double[][] mat1 = mo.buildMatrix("Matrix1.txt");
			double[][] mat2 = mo.buildMatrix("Matrix2.txt");

			if (mat1.length == mat2.length && mat1[0].length == mat2[0].length) {
				s1 = mo.printArray(mat1);
				s2 = mo.printArray(mat2);

				matrixAdd = mo.addMatrices(mat1, mat2, mat1.length, mat1[0].length);
				matrixSquare = mo.squareMatrices(mat1, mat1.length, mat1[0].length);

				sAdd = mo.printArray(matrixAdd);
				sSq = mo.printArray(matrixSquare);
				
				s = s1 + s2 + sAdd + sSq;
			} else {
				s = "Please make sure that you have matrices of same dimensions.";
			}

		}
		matrix.setResult(s);
		model.addAttribute("matrixResult", matrix.getResult());
		model.addAttribute("matrix", new Matrix());
		return "Answers.html";
	}

}
