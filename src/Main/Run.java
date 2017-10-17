package Main;

import java.util.Scanner;

public class Run {
	int size;
	int[] det;
	int[][] newMatrix;
	int[][][] matrix;
	
	public static void main(String[] args) {
		Run Run = new Run();
	}
	
	public Run() {
		start:
		do {
			Scanner selection = new Scanner(System.in);
			System.out.println("****************************");
			System.out.println("Select the function you want.");
			System.out.println("---------------------------------");
			System.out.println("A - Addition");
			System.out.println("S - Subtraction");
			System.out.println("M - Multiplication");
			System.out.println("D - Determinant");
			System.out.println("I - Inverse");
			System.out.println("****************************");
			String selected = selection.nextLine().toUpperCase();
			if (selected.equals("A") || selected.equals("S") || selected.equals("M") || selected.equals("D") || selected.equals("I")) {
				input(selected);
			} else {
				continue start;
			}
			
			newMatrix = new int[size][size];
			switch (selected) {
				case "A" :
					addition();
					output(newMatrix, size);
					break;
					
				case "S" :
					subtraction();
					output(newMatrix, size);
					break;
					
				case "M" :
					multiplication();
					output(newMatrix, size);
					break;
					
				case "D" :
					det = new int[size - 2];
					for (int L = 1; L < size - 2; L++) {
						//indicate working on which number in parent matrix
						for (int pos = 0; pos < size - L + 1; pos++) {
							//operate here
							minor(pos, L);
							determinant_3x3();
							output2(1, matrix, size);
						}
					}
					break;
					
				case "I" :
					//mycode
					break;
					
				default :
					continue start;
			}
			
		}while(true);
	}
	
	
	//done!
	void input(String s) {
		Scanner scanS = new Scanner(System.in);
    
		System.out.println("Please enter the size of the matrix :");
		size = scanS.nextInt();

		Scanner scanM = new Scanner(System.in);
		if (s.equals("D") || s.equals("I")) {
			matrix = new int[size - 2][size][size];
			for (int y = 0; y < size; y++) {
				System.out.println("Please enter the " + (y + 1) + " row of matrix :");
				for (int x = 0; x < size; x++) {
					matrix[0][y][x] = scanM.nextInt();
				}
			}
		} else {
			matrix = new int[2][size][size];
			for (int z = 0; z < 2; z++) {
				for (int y = 0; y < size; y++) {
					System.out.println("Please enter the " + (y + 1) + " row of " + (z + 1) + " matrix :");
					for (int x = 0; x < size; x++) {
						matrix[z][y][x] = scanM.nextInt();
					}
				}
			}
		}
	}
	
	//done!
	void addition() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[0][y][x] + matrix[1][y][x];
			}
		}
	}
	
	//done!
	void subtraction() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[0][y][x] - matrix[1][y][x];
			}
		}
	}
	
	//done!
	 void multiplication() {
		int newy, newx, z = 0;
		for (newy = 0; newy < size; newy++) {
			for (newx = 0; newx < size; newx++) {
				for (int i = 0; i < size; i++) {
					newMatrix[newy][newx] += matrix[z][newy][i] * matrix[(z + 1)][i][newx];
				}
			}
		}
	}
	
	 
	 //done!
	void minor(int P, int L) { 	//L should be start with 1, minor should also be same matrix
		int dx, dy, minor_size = (size - L);
		//get the minor
		for (dy = 0; dy < minor_size; dy++) {
			for (dx = 0; dx < P; dx++) {
				matrix[L][dy][dx] = matrix[L - 1][dy + 1][dx];
			}
			for (dx = P; dx < minor_size; dx++) {
				matrix[L][dy][dx] = matrix[L - 1][dy + 1][dx + 1];
			}
		}
	}
	
	//done!!
	void determinant_3x3() {
		int minor_pos, temp, step;
		for (minor_pos = 0; minor_pos < 3; minor_pos++) {
			temp = 1;
			for (step = 0; step < 3; step++) {
				if ((minor_pos + step) >= 3) {
					temp *= matrix[size - 3][step][(minor_pos + step) % 3];
				} else {
					temp *= matrix[size - 3][step][minor_pos + step];
				}
			}
			det[size - 3] += temp;
		}
		
		for (minor_pos = 3; minor_pos >= 0; minor_pos--) {
			temp = 1;
			for (step = 0; step < 3; step++) {
				if((minor_pos - step) < 0) {
					temp *= matrix[size - 3][step][minor_pos - step + 3];
				} else {
					temp *= matrix[size - 3][step][minor_pos - step];
				}
			}
			det[size - 3] -= temp;
		}
	}
	
	void determinant(int L) {		//L should be started with 1
		int pos, minor_size = (size - L), minor_pos, temp, step, result = 0, subresult;
		//indicate working on which number in parent matrix
		for (pos = 0; pos < size - L; pos++) {
			subresult = 0;
			//calculate minor determinant
			for (minor_pos = 0; minor_pos < minor_size; minor_pos++) {
				temp = 1;
				for (step = 0; step < minor_size; step++) {
					if ((minor_pos + step) >= minor_size) {
						temp *= matrix[L][step][(minor_pos + step) % minor_size];
					} else {
						temp *= matrix[L][step][minor_pos + step];
					}
				}
				subresult += temp;
			}
			
			for (minor_pos = minor_size - 1; minor_pos >= 0; minor_pos--) {
				temp = 1;
				for (step = 0; step < minor_size; step++) {
					if((minor_pos - step) < 0) {
						temp *= matrix[L][step][minor_pos - step + minor_size];
					} else {
						temp *= matrix[L][step][minor_pos - step];
					}
				}
				subresult -= temp;
			}
			if (pos % 2 == 0) {
				result += matrix[L - 1][0][pos] * subresult;
			} else {
				result -= matrix[L - 1][0][pos] * subresult;
			}
		}
		System.out.println("The determinant is " + result);
	}
	
	void output(int[][] M, int S) {
		for (int y = 0; y < S; y++) {
			System.out.print("{ ");
			for (int x = 0; x < S - 1; x++) {
				System.out.print(M[y][x] + ", ");
			}
			System.out.print(M[y][(S - 1)] + " };\n");
		}
	}
	
	void output2(int L, int[][][] M, int S) {
		for (int y = 0; y < S; y++) {
			System.out.print("{ ");
			for (int x = 0; x < S - 1; x++) {
				System.out.print(M[L][y][x] + ", ");
			}
			System.out.print(M[L][y][(S - 1)] + " };\n");
		}
	}
	
	void output_test() {
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