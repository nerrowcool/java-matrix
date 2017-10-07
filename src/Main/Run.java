package Main;

import java.util.Scanner;

public class Run {
	int size;
	int[][] newMatrix;
	int[][][] matrix;
	
	public static void main(String[] args) {
		Run Run = new Run();
	}
	
	public Run() {
		start:
		do {
			Scanner selection = new Scanner(System.in);
			System.out.println("Select the function you want.");
			System.out.println("---------------------------------");
			System.out.println("A - Addition");
			System.out.println("S - Subtraction");
			System.out.println("M - Multiplication");
			System.out.println("D - Determinant");
			System.out.println("I - Inverse");
			String selected = selection.nextLine();
			
			input();
			output_test();
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					newMatrix[y][x] = 0;
				}
			}
			switch (selected.toUpperCase()) {
				case "A" :
					addition();
					break;
					
				case "S" :
					subtraction();
					break;
					
				case "M" :
					multiplication();
					break;
					
				case "D" :
					//mycode
					break;
					
				case "I" :
					//mycode
					break;
					
				default :
					continue start;
			}
			
			output();
		}while(true);
	}
	
	public void input() {
		Scanner scanS = new Scanner(System.in);
    
		System.out.println("Please enter the size of the matrix :");
		size = scanS.nextInt();

		matrix = new int[2][size][size];
		Scanner scanM = new Scanner(System.in);

		for (int z = 0; z < 2; z++) {
			for (int y = 0; y < size; y++) {
				System.out.println("Please enter the " + (y + 1) + " row of " + (z + 1) + " matrix :");
				for (int x = 0; x < size; x++) {
					matrix[z][y][x] = scanM.nextInt();
				}
			}
		}
	}
	
	public void addition() {
		newMatrix = new int[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[1][y][x] + matrix[2][y][x];
			}
		}
	}
	
	public void subtraction() {
		newMatrix = new int[size][size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[1][y][x] - matrix[2][y][x];
			}
		}
	}
	
	public void multiplication() {
		int newy, newx, z = 0;
		newMatrix = new int[size][size];
		for (newy = 0; newy < size; newy++) {
			for (newx = 0; newx < size; newx++) {
				for (int i = 0; i < size; i++) {
					newMatrix[newy][newx] += matrix[z][newy][i] * matrix[(z + 1)][i][newx];
				}
			}
		}
	}
	
	public void determinant() {
		
	}
	
	public void output() {
		for (int y = 0; y < size; y++) {
			System.out.print("{ ");
			for (int x = 0; x < size - 1; x++) {
				System.out.print(newMatrix[y][x] + ", ");
			}
			System.out.print(newMatrix[y][(size - 1)] + " };\n");
		}
	}
	
	public void output_test() {
		for (int z = 0; z < 2; z++) {
			for (int y = 0; y < size; y++) {
				System.out.print("{ ");
				for (int x = 0; x < size - 1; x++) {
					System.out.print(matrix[z][y][x] + ", ");
				}
				System.out.print(matrix[z][y][(size - 1)] + " };\n");
			}
		}
	}
}