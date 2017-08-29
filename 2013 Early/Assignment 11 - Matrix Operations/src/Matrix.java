
public class Matrix {
	private int row, col;
	int[][] matrix;
	
	
	public Matrix(int row, int col, int[][] matrix){
		this.row = row;
		this.col = col;
		this.matrix = matrix;
	}
	
	
	public int[][] getMatrix(){
		return matrix;
	}
	
	public int getRowSize(){
		return row;
	}
	
	public int getColSize(){
		return col;
	}
	
	public void setElements(int[][] elements){
		if (elements.length == row && elements[0].length == col){
			matrix = elements;
		}else{
			System.err.println("Selected array is of an incorrect size.");
		}
	}
	
	public Matrix addMatrix(Matrix input){
		int[][] newMatrix = new int[row][col];
		int[][] inputArray = input.getMatrix();
		
		for (int i=0;i<row;i++){
			for (int j=0;j<col;j++){
				newMatrix[i][j] = inputArray[i][j] + matrix[i][j];
			}
		}
		
		Matrix addMatrix = new Matrix(row, col, newMatrix);
		return addMatrix;
	}
	
	public Matrix subtractMatrix(Matrix input){
		int[][] newMatrix = new int[row][col];
		int[][] inputArray = input.getMatrix();
		
		for (int i=0;i<row;i++){
			for (int j=0;j<col;j++){
				newMatrix[i][j] = matrix[i][j] - inputArray[i][j];
			}
		}
		
		Matrix subtractMatrix = new Matrix(row, col, newMatrix);
		return subtractMatrix;
	}
}
