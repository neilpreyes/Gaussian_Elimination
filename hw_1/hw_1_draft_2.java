package hw_1;
/**
 * Neil Patrick Reyes
 * CS 3010 Section 03
 * Professor Raheja
 * 10/03/2022
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class hw_1_draft_2{
	
	public static boolean wasPivot(int[] arr, int num, int arrLength) {
		boolean has = false;
		
		for(int i = 0; i < arrLength; i++) {
			if(arr[i] == num) {
				has = true;
			}
		}
		
		return has;
	}
	
	public static double[] gaussian(double[][] matrix, int numEquations){
		double[] values = new double[numEquations];
		double[] maxNum = new double[numEquations];
		double[][] newMatrix = new double[numEquations][numEquations+1];
		
		for(int i = 0; i < numEquations; i++) {
			maxNum[i] = 0;
		}
		
		//Scale factor
		for(int i = 0; i < numEquations; i++) {
			for(int j = 0; j < numEquations; j++) {
				if(maxNum[i] < Math.abs(matrix[i][j])) {
					maxNum[i] = Math.abs(matrix[i][j]);
				}
			}
		}
		
		int maxRatioRow = 0;
		double maxRatio = 0;
		int[] pivot = new int[numEquations];
		int[] remainingPivot = new int[numEquations];
		
		for(int i = 0; i < numEquations; i++) {
			remainingPivot[i] = i;
			pivot[i] = -1;
		}
		
		//pivots for as many equations
		for(int numPivot = 0; numPivot < numEquations; numPivot++) {
			if(numPivot == numEquations-1) {
				for(int i = 0; i < numEquations; i++) {
					if(remainingPivot[i] != -1) {
						pivot[numEquations-1] = remainingPivot[i];
						break;
					}
				}
			}else {
				double[] ratios = new double[numEquations];
				//System.out.println("Ratio Check: ");
				for(int i = 0; i < numEquations; i++) {//calculates ratios
					if(wasPivot(pivot,i,numEquations)) {
						ratios[i] = 0;
						//System.out.print(0 + " ");
					}else {
						ratios[i] = matrix[i][numPivot] / Math.abs(maxNum[i]);
						//System.out.print(ratios[i] + " ");
					}
				}
				//System.out.println();
				
				//System.out.println("Max Ratio Check");
				maxRatio = 0;
				for(int i = 0; i < numEquations; i++) {//sets pivot row
					if(maxRatio < Math.abs(ratios[i])) {
						maxRatio = Math.abs(ratios[i]);
						maxRatioRow = i;
						//System.out.println("Max Ratio is now: " + ratios[i]);
						//System.out.print(" on line #" + i);
					}
				}
				remainingPivot[maxRatioRow] = -1;
				pivot[numPivot] = maxRatioRow;
				System.out.println("\nPivot #" + (numPivot+1));
				for(int i = 0; i < numEquations; i++) {
					for(int j = 0; j <= numEquations; j++) {
						if(wasPivot(pivot, i, numEquations)) {
							newMatrix[i][j] = matrix[i][j];
						}else {
							newMatrix[i][j] = matrix[i][j] - (((matrix[i][numPivot] / matrix[maxRatioRow][numPivot]))
									 * (matrix[maxRatioRow][j]));
						}
						System.out.print(newMatrix[i][j] + " ");
					}
					System.out.println();
				}
				//System.out.println("Copy in progress.");
				for(int i = 0; i < numEquations; i++) {
					for(int j = 0; j < numEquations + 1; j++) {
						matrix[i][j] = newMatrix[i][j];
						//System.out.print(matrix[i][j] + " ");
					}
					//System.out.println();
				}
			}
		}
		
		
		/**
		 * System.out.println("\nRatio Order Check");
		for(int i = 0; i < numEquations; i++) {
			System.out.print(pivot[i] + " ");
		}
		 */
		 
		
		double answer;
		int arrLast = numEquations-1;
		for(int i = 0; i < numEquations; i++) {
			switch(i) {
			case 0:
				//System.out.println(newMatrix[pivot[arrLast]][numEquations] + " / " + newMatrix[pivot[arrLast]][arrLast]);
				answer = newMatrix[pivot[arrLast]][numEquations]/newMatrix[pivot[arrLast]][arrLast];
				values[arrLast] = Math.round(answer * 100.0)/100.0;
				break;
			case 1:
				answer = (newMatrix[pivot[arrLast-1]][numEquations] - (values[arrLast] * newMatrix[pivot[arrLast-1]][numEquations-1]))/newMatrix[pivot[arrLast-1]][arrLast-1];
				values[arrLast-1] = Math.round(answer * 100.0)/100.0;
				break;
			case 2:
				answer = (newMatrix[pivot[arrLast-2]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-2]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-2]][numEquations-2]))) / newMatrix[pivot[arrLast-2]][arrLast-2];
				values[arrLast-2] = Math.round(answer * 100.0)/100.0;
				break;
			case 3:
				answer = (newMatrix[pivot[arrLast-3]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-3]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-3]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-3]][numEquations-3]))) 
				/ newMatrix[pivot[arrLast-3]][arrLast-3];
				values[arrLast-3] = Math.round(answer * 100.0)/100.0;
				break;
			case 4:
				answer = (newMatrix[pivot[arrLast-4]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-4]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-4]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-4]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-4]][numEquations-4]))) / newMatrix[pivot[arrLast-4]][arrLast-4];
				values[arrLast-4] = Math.round(answer * 100.0)/100.0;
				break;
			case 5:
				answer = (newMatrix[pivot[arrLast-5]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-5]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-5]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-5]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-5]][numEquations-4]) + (values[arrLast-4] * newMatrix[pivot[arrLast-5]][numEquations-5]))) 
				/ newMatrix[pivot[arrLast-5]][arrLast-5];
				values[arrLast-5] = Math.round(answer * 100.0)/100.0;
				break;
			case 6:
				answer = (newMatrix[pivot[arrLast-6]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-6]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-6]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-6]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-6]][numEquations-4]) + (values[arrLast-4] * newMatrix[pivot[arrLast-6]][numEquations-5]) +
						(values[arrLast-5] * newMatrix[pivot[arrLast-6]][numEquations-6]))) / newMatrix[pivot[arrLast-6]][arrLast-6];
				values[arrLast-6] = Math.round(answer * 100.0)/100.0;
				break;
			case 7:
				answer = (newMatrix[pivot[arrLast-7]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-7]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-7]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-7]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-7]][numEquations-4]) + (values[arrLast-4] * newMatrix[pivot[arrLast-7]][numEquations-5]) +
						(values[arrLast-5] * newMatrix[pivot[arrLast-7]][numEquations-6]) + (values[arrLast-6] * newMatrix[pivot[arrLast-7]][numEquations-7]))) 
				/ newMatrix[pivot[arrLast-7]][arrLast-7];
				values[arrLast-7] = Math.round(answer * 100.0)/100.0;
				break;
			case 8:
				answer = (newMatrix[pivot[arrLast-8]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-8]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-8]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-8]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-8]][numEquations-4]) + (values[arrLast-4] * newMatrix[pivot[arrLast-8]][numEquations-5]) +
						(values[arrLast-5] * newMatrix[pivot[arrLast-8]][numEquations-6]) + (values[arrLast-6] * newMatrix[pivot[arrLast-8]][numEquations-7]) +
						(values[arrLast-7] * newMatrix[pivot[arrLast-8]][numEquations-8]))) / newMatrix[pivot[arrLast-8]][arrLast-8];
				values[arrLast-8] = Math.round(answer * 100.0)/100.0;
				break;
			case 9:
				answer = (newMatrix[pivot[arrLast-9]][numEquations] - ((values[arrLast] * newMatrix[pivot[arrLast-9]][numEquations-1]) + 
						(values[arrLast-1] * newMatrix[pivot[arrLast-9]][numEquations-2]) + (values[arrLast-2] * newMatrix[pivot[arrLast-9]][numEquations-3]) +
						(values[arrLast-3] * newMatrix[pivot[arrLast-9]][numEquations-4]) + (values[arrLast-4] * newMatrix[pivot[arrLast-9]][numEquations-5]) +
						(values[arrLast-5] * newMatrix[pivot[arrLast-9]][numEquations-6]) + (values[arrLast-6] * newMatrix[pivot[arrLast-9]][numEquations-7]) +
						(values[arrLast-7] * newMatrix[pivot[arrLast-9]][numEquations-8]) + (values[arrLast-8] * newMatrix[pivot[arrLast-9]][numEquations-9]))) 
				/ newMatrix[pivot[arrLast-9]][arrLast-9];
				values[arrLast-9] = Math.round(answer * 100.0)/100.0;
				break;
			default:
				System.out.println("Out of range");
				break;
			}
		}
		
		
		return values;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//initialize scanner
		Scanner scan = new Scanner(System.in);
		
		//set number of equations
		//3 is minimum for x,y,z variables and 10 is max limit
		int numOfEquations;
		do {
			System.out.println("How many equations do you have? (Please input an integer between 3 and 10.)");
			numOfEquations = scan.nextInt();
		}while(numOfEquations > 10 || numOfEquations < 2);
		

		
		//create matrix of equations
		double[][] equations = new double[numOfEquations][numOfEquations+1];
		
		char text;
		boolean complete;
		
		do { //use input.txt as a test to see if input method works correctly
			complete = false;
			System.out.println("Is the input a txt file? (Answer only in Y/N)");
			text = scan.next().charAt(0);
			if(text == 'Y' || text == 'y') {
				String file;
				System.out.println("Please input the path of the file:");
				file = scan.next();
				Scanner textWrite = new Scanner(new File(file));
				for(int i = 0; i < numOfEquations; i++) {
					for(int j = 0; j <= numOfEquations; j++) {
						equations[i][j] = textWrite.nextInt();
					}
				}
			}else if(text == 'N' || text == 'n'){
				//set values for matrix
				//double x, y, z, result;
				for(int i = 0; i < numOfEquations; i++) {
					System.out.println("Please input the cooefficients one at a time " +
				 "then follow them with the resulting number");
					for(int j = 0; j <= numOfEquations; j++) {
						if(j != numOfEquations) {
							System.out.println("Coefficient x" + j + " equals: ");
							equations[i][j] = scan.nextInt();
						}
						else {
							System.out.println("Result equals: ");
							equations[i][j] = scan.nextInt();
						}
					}
				}
			}else {
				System.out.println("Invalid input please try again.\n");
				complete = true;
			}
		}while(complete);
		
		//gaussian uses pivot method to find values of x, y, and z
		double[] finalAnswer = new double [numOfEquations];
		finalAnswer = gaussian(equations, numOfEquations);
		
		//prints out final answer
		for(int i = 0; i < numOfEquations; i++) {
			System.out.println("\nThe value for variable x" + (i) + " is " + finalAnswer[i]);
		}
		
		scan.close();
		
	}
}
