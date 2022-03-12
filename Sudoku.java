public class Sudoku{
    public static final int size = 9;

    public static boolean isNumberPresentInRow(int[][] board, int number, int row){
        for(int i=0;i<size;i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }
    public static boolean isNumberPresentInColumn(int[][] board, int number, int col){
        for(int i=0;i<size;i++){
            if(board[i][col] == number){
                return true;
            }
        }
        return false;
    }
    public static boolean isNumberPresentInInnerBox(int[][] board, int number, int row, int col){
       int innerBoxRow = row - row % 3;
       int innerBoxCol = col - col % 3;
       for(int i=innerBoxRow;i<innerBoxRow+3;i++){
           for(int j=innerBoxCol;j<innerBoxCol+3;j++){
                if(board[i][j] == number){
                    return true;
                }
           }
       }
       return false;
    }

    public static boolean isValid(int[][] board, int number, int row, int col){
        return !isNumberPresentInRow(board, number, row) &&
                !isNumberPresentInColumn(board, number, col) &&
                 !isNumberPresentInInnerBox(board, number, row, col);
    }

    public static boolean solveSudoku(int[][] board){
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                if(board[row][col] == 0){
                    for(int number=1;number<=9;number++){
                        if(isValid(board, number, row, col)){
                            board[row][col] = number;
                            if(solveSudoku(board)){
                                return true;
                            }
                            else{
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void displaySudoku(int[][] board){
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
        };      
        
        displaySudoku(board);

        if(solveSudoku(board)){
            System.out.println("Sudoku Solved...");
            displaySudoku(board);
        }
        else{
            System.out.println("Sudoku Unsolvable...");
        }
    }
}