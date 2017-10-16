package Main;

import java.util.Scanner;

public class Run {
	int size;
	int[][] newMatrix, minor;
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
			System.out.println(selected);
			
			input(selected);

			newMatrix = new int[size][size];
			minor = new int[size - 1][size - 1];
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
					determinant();
					break;
					
				case "I" :
					//mycode
					break;
					
				default :
					continue start;
			}
			
		}while(true);
	}
	
	void input(String s) {
		Scanner scanS = new Scanner(System.in);
    
		System.out.println("Please enter the size of the matrix :");
		size = scanS.nextInt();

		matrix = new int[2][size][size];
		Scanner scanM = new Scanner(System.in);
		if (s.equals("D") || s.equals("I")) {
			for (int y = 0; y < size; y++) {
				System.out.println("Please enter the " + (y + 1) + " row of matrix :");
				for (int x = 0; x < size; x++) {
					matrix[0][y][x] = scanM.nextInt();
				}
			}
		} else {
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
	
	void addition() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[0][y][x] + matrix[1][y][x];
			}
		}
	}
	
	void subtraction() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				newMatrix[y][x] = matrix[0][y][x] - matrix[1][y][x];
			}
		}
	}
	
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
	
	void determinant() {
		int count, minor_size = (size - 1), result = 0, sub_result = 0;
		for ( int loop = 0; loop < size; loop++) {
			//get the minor
			for (int dy = 0; dy < minor_size; dy++) {
				count = 0;
				for (int dx = 0; dx < loop; dx++) {
					minor[dy][dx] = matrix[0][dy + 1][dx];
					count++;
				}
				for (int x = count; x < minor_size; x++) {
					minor[dy][x] = matrix[0][dy + 1][x + 1];
				}
			}
			//calculate minor determinant
			for (int x = 0; x < size; x++) {
				for (int add = 0; add < minor_size; add++) {
					System.out.println("Adding");
					//System.out.println("Add is " + add);
					int temp = 1;
					for (int step = 0; step < minor_size; step++) {
						//System.out.println("Step is " + step);
						if ((add + step) >= minor_size) {
							System.out.println("step = " + step + "\nadd + step = " + ((add + step) % minor_size));
							temp *= minor[step][(add + step) % minor_size];
						} else {
							System.out.println("step = " + step + "\nadd + step = " + (add + step));
							temp *= minor[step][add + step];
						}
						System.out.println("temp = " +temp);
					}
					sub_result += temp;
					System.out.println("sub_result = " + sub_result);
				}
				for (int sub = minor_size - 1; sub >= 0; sub--) {
					System.out.println("Subing");
					int temp = 1;
					for (int step = 0; step < minor_size; step++) {
						if((sub - step) < 0) {
							System.out.println("step = " + step + "\nMath.abs(sub - step) % minor_size = " + (Math.abs(sub - step) % minor_size));
							temp *= minor[step][Math.abs(sub - step) % minor_size];
						} else {
							System.out.println("step = " + step + "\nsub - step = " + (sub - step));
							temp *= minor[step][sub - step];
						}
						System.out.println("temp = " +temp);
					}
					sub_result -= temp;
					System.out.println("sub_result = " + sub_result);
				}
			}
			result += matrix[0][0][loop] * sub_result;
		}
		output2(matrix, size);
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
	
	void output2(int[][][] M, int S) {
		for (int y = 0; y < S; y++) {
			System.out.print("{ ");
			for (int x = 0; x < S - 1; x++) {
				System.out.print(M[0][y][x] + ", ");
			}
			System.out.print(M[0][y][(S - 1)] + " };\n");
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