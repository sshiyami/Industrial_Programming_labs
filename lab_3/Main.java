import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    // Создать матрицу
    private static Integer[][] makeMatrix() {
        int n, m;
        System.out.println("Enter size of matrix(n, m)");
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        if(n < 2) {
            n = 2;
        }
        m = input.nextInt();
        if(m < 2) {
            m = 2;
        }
        Integer[][] matrix = new Integer[n][m];
        for(Integer[] i : matrix) {
            for(int j = 0; j < i.length; j++) {
                i[j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    private static void printMatrix(Integer[][] matrix) {
        int moneyRow = 0;
        int numberRow = 1;
        NumberFormat money = NumberFormat.getCurrencyInstance();
        NumberFormat number = NumberFormat.getNumberInstance();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(i != moneyRow) {
                    System.out.print(matrix[i][j] + " ");
                } else if(i == moneyRow) {
                    System.out.print(money.format(matrix[i][j]) + " ");
                } else if(i == numberRow) {
                    System.out.print(number.format(matrix[i][j]) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Свап рядов
    private static void swapRows(Integer[][] matrix, int row1, int row2) throws IndexOutOfBoundsException{
        if(row1 >= matrix.length || row2 >= matrix.length || row1 < 0 || row2 < 0) {
            throw new IndexOutOfBoundsException("Incorrect row!");
        }

        Integer[] tempRow = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = tempRow;
    }

    // Свап столбцов
    private static void swapColumns(Integer[][] matrix, int col1, int col2) throws IndexOutOfBoundsException {
        if(col1 >= matrix[0].length || col2 >= matrix[0].length || col1 < 0 || col2 < 0) {
            throw new IndexOutOfBoundsException("Incorrect column!");
        }

        Arrays.stream(matrix).forEach(row -> {
            int temp = row[col2];
            row[col2] = row[col1];
            row[col1] = temp;
        });
    }

    private static void moveMaxElement(Integer[][] matrix) {
        int rowMax = 0;
        int colMax = 0;
        Integer maxElement = matrix[0][0];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] > maxElement) {
                    rowMax = i;
                    colMax = j;
                    maxElement = matrix[i][j];
                }
            }
        }

        swapRows(matrix, rowMax, 0);
        swapColumns(matrix, colMax, 0);
    }

    private static void sortLastRow(Integer[][] matrix) {
        int lastRow = matrix.length - 1;
        for(int i = 0; i < matrix[0].length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(Integer.compare(matrix[lastRow][j], matrix[lastRow][i]) < 0) { 
                    Integer temp = matrix[lastRow][i];
                    matrix[lastRow][i] = matrix[lastRow][j];
                    matrix[lastRow][j] = temp;
                }
            }
        }
    }

    private static Integer[] copyOfRange(Integer[] source, int firstEl, int lastEl)
            throws  IndexOutOfBoundsException{
        if(source.length < firstEl || firstEl < 0) {
            throw new IndexOutOfBoundsException("Function copyOfRange: Incorrect first element!");
        }
        if(source.length < lastEl || lastEl < 0) {
            throw new IndexOutOfBoundsException("Function copyOfRange: Incorrect last element!");
        }

        return Arrays.copyOfRange(source, firstEl, lastEl + 1);
    }

    private static void partCopyRow(Integer[][] matrixToCopy, int rowToCopy,
                                    int firstEl, int lastEl, Integer[][] matrix, int row, int pos)
                                    throws IndexOutOfBoundsException{
        if(lastEl - firstEl > matrix[row].length) {
            throw new IndexOutOfBoundsException("Function partCopyRow: incorrect range!");
        }
        if(pos < 0 || pos > matrix[row].length) {
            throw new IndexOutOfBoundsException("Function partCopyRow: incorrect position!");
        }
        Integer[] tempRow = copyOfRange(matrixToCopy[rowToCopy], firstEl, lastEl);
        System.arraycopy(tempRow, 0, matrix[row], pos, tempRow.length);
    }

    private static void partCopyByUser(Integer[][] matrixToCopy, Integer[][] matrix) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter row to copy, first element, last element, row, position:");
        partCopyRow(matrixToCopy, input.nextInt() - 1, input.nextInt() - 1, input.nextInt() - 1,
                matrix,input.nextInt() - 1, input.nextInt() - 1);
    }

    public static void main(String[] args) {
        try {
            Integer[][] matrix = makeMatrix();
            printMatrix(matrix);

            moveMaxElement(matrix);
            printMatrix(matrix);

            sortLastRow(matrix);
            printMatrix(matrix);

            Integer[][] tempMatrix = makeMatrix();
            printMatrix(tempMatrix);

            partCopyByUser(matrix, tempMatrix);
            printMatrix(tempMatrix);

        } catch(IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
