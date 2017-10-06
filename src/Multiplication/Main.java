package Multiplication;

import java.util.Scanner;

public class Main {
  
	public static void main(String[] args) {
		Scanner scanS = new Scanner(System.in);
    

		System.out.println("Please enter the size of the matrix :");
		int size = scanS.nextInt();

		int[][][] matrix = new int[2][size][size];
		Scanner scanM = new Scanner(System.in);


		for (int z = 0; z < 2; z++) {
			for (int y = 0; y < size; y++) {
				System.out.println("Please enter the " + (y + 1) + " row of " + (z + 1) + " matrix :");
				for (int x = 0; x < size; x++) {
					matrix[z][y][x] = scanM.nextInt();
				}
			}
		}

		scanS.close();
		scanM.close();

		int a = 0;
		int[][] newMatrix = new int[size][size];
		for (int newy = 0; newy < size; newy++) {
			for (int newx = 0; newx < size; newx++) {
				for (int i = 0; i < size; i++) {
					newMatrix[newy][newx] += matrix[a][newy][i] * matrix[(a + 1)][i][newx];
				}
			}
}


		int newy, newx;
		for (newy = 0; newy < size; newy++) {
			System.out.print("{ ");
			for (newx = 0; newx < size - 1; newx++) {
				System.out.print(newMatrix[newy][newx] + ", ");
			}
			System.out.print(newMatrix[newy][(size - 1)] + " };");
			System.out.print("\n");
		}
	}
}