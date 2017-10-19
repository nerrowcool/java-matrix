package Main;

public class determinant {
	void minor(int L, int size, int[][][] matrix) { // L should be start with 1
		//indicate working on which element
		for (int pos = 0; pos < size - L + 1; pos++) {
			//generate a minor from top to bottom
			for (int minor_y = 0; minor_y < size - L; minor_y++) {
				//skip the element in same column with the one working on
				for (int minor_x = 0; minor_x < pos; minor_x++) {
					matrix[L][minor_y][minor_x] = matrix[L - 1][minor_y + 1][minor_x];
				}
				for (int minor_x = pos; minor_x < size - L; minor_x++) {
					matrix[L][minor_y][minor_x] = matrix[L - 1][minor_y + 1][minor_x + 1];
				}
			}
			//should had a minor of the working element already
			
			//test
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					System.out.print(matrix[L][y][x] + ", ");
				}
			}
		}
	}
}
