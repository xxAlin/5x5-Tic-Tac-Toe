
import java.util.*;

class Point { //class Point with its constructor
    int x, y;

    public Point(int x, int y) { //parameters
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + (x+1) + ", " + (y+1) + "]";
    } // toString() method which returns a string
}

class PointsAndScores { //class PointsAndScores with its constructor
    int score;
    Point point;

    PointsAndScores(int score, Point point) { //parameters
        this.score = score;
        this.point = point;
    }
}

@SuppressWarnings("Duplicates")
class Board {
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[5][5];

    public Board() {
    }

    // returns hasXWon() if X won / hasOWon() if O won / getAvailablePoints().isEmpty() if it's a draw
    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }

    public boolean hasXWon() {
        //checks if X wins on left diagonal or right diagonal
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4] && board[0][0] == 1)
                || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0] && board[0][4] == 1)) {
            return true;
        }
        //checks if X wins on horizontal line or vertical line
        for (int i = 0; i < 5; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][3] && board[i][0] == board[i][4] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        //checks if O wins on left diagonal or right diagonal
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4] && board[0][0] == 2)
                || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[3][1] && board[0][4] == board[4][0] && board[0][4] == 2)) {
            return true;
        }
        //checks if O wins on horizontal line or vertical line
        for (int i = 0; i < 5; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][3] && board[i][0] == board[i][4] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 2))) {
                return true;
            }
        }
        return false;
    }

    //counts the number of X's or O's on a vertical line.  The linePoints represent 10^n or -(10^n) (n represent the number of O's or X's). If the line got both O's or X's then linePoints = 0
    int countVerticalLinePoints(int column) {
        int noughts = 0;
        int crosses = 0;
        int linePoints = 0;
        boolean lineHasCross = false;
        boolean lineHasNought = false;
        for (int y = 0; y < 5; y++) {
            int x = column;
            if (board[x][y] == 1) {
                lineHasCross = true;
                crosses = crosses + 1;
            }
            if (board[x][y] == 2) {
                lineHasNought = true;
                noughts = noughts + 1;
            }
        }
        if (lineHasCross && lineHasNought) { linePoints = 0; }
        else if (lineHasCross)  { linePoints = (int) Math.pow(10, crosses); }
        else if (lineHasNought) { linePoints = (int) -Math.pow(10, noughts); }
        return linePoints;
        }
    //counts the number of X's or O's on a horizontal line. The linePoints represent 10^n or -(10^n) (n represent the number of O's or X's). If the line got both O's or X's then linePoints = 0
    int countHorizontalLinePoints(int row) {
        int noughts = 0;
        int crosses = 0;
        int linePoints = 0;
        boolean lineHasCross = false;
        boolean lineHasNought = false;
        for (int x = 0; x < 5; x++){
            int y = row;
            if (board[x][y] == 1) {
                lineHasCross = true;
                crosses = crosses + 1;
            }
            if (board[x][y] == 2) {
                lineHasNought = true;
                noughts = noughts + 1;
            }
        }
        if (lineHasCross && lineHasNought) { linePoints = 0; }
        else if (lineHasCross)  { linePoints = (int) Math.pow(10, crosses); }
        else if (lineHasNought) { linePoints = (int) -Math.pow(10, noughts); }
        return linePoints;
    }

    //counts the number of X's or O's on the Left Diagonal.  The linePoints represent 10^n or -(10^n) (n represent the number of O's or X's). If the line got both O's or X's then linePoints = 0
    int countLeftDiagonalPoints() {
        int noughts = 0;
        int crosses = 0;
        int linePoints = 0;
        boolean lineHasNought = false;
        boolean lineHasCross = false;
        for (int x = 0; x < 5; x++){
            int y = 4 - x;
            if (board[x][y] == 1) {
                lineHasCross = true;
                crosses = crosses + 1;
            }
            if (board[x][y] == 2) {
                lineHasNought = true;
                noughts = noughts + 1;
            }
        }
        if (lineHasCross && lineHasNought) { linePoints = 0; }
        else if (lineHasCross)  { linePoints = (int) Math.pow(10, crosses); }
        else if (lineHasNought) { linePoints = (int) -Math.pow(10, noughts); }
        return linePoints;
    }

    //counts the number of X's or O's on the right diagonal.  The linePoints represent 10^n or -(10^n) (n represent the number of O's or X's). If the line got both O's or X's then linePoints = 0
    int countRightDiagonalPoints() {
        int noughts = 0;
        int crosses = 0;
        int linePoints = 0;
        boolean lineHasNought = false;
        boolean lineHasCross = false;
        for (int x = 0; x < 5; x++) {
            int y = x;
            if (board[x][y] == 1) {
                lineHasCross = true;
                crosses = crosses + 1;
            }
            if (board[x][y] == 2) {
                lineHasNought = true;
                noughts = noughts + 1;
            }
        }
        if (lineHasCross && lineHasNought) { linePoints = 0; }
        else if (lineHasCross)  { linePoints = (int) Math.pow(10, crosses); }
        else if (lineHasNought) { linePoints = (int) -Math.pow(10, noughts); }
        return linePoints;

    }

    //the linePoints of every method are all summed
    public int heuristicTotal() {
        int points = 0;
        for (int column = 0; column < 5; column++)
        {
            points += countVerticalLinePoints(column);
        }
        for (int row = 0; row < 5; row++)
        {
            points += countHorizontalLinePoints(row);
        }
        points += countLeftDiagonalPoints();
        points += countRightDiagonalPoints();
        return points; // points represent the sum of all methods above
    }

    /* This is a method which stores every point with coordinates from the grid in a list. If the point on the grid is not
    occupied by X or O the point is gonna be stored in the list then at the end of the method the list is returned*/
    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    //method which helps us get the coordinates of a point
    public int getState(Point point){
        return board[point.x][point.y];
    }

    //method which places a move on a point of coordinates x and y (x is the line, y is the row). the move is made by a player(1 or 2)
    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;
    }

    // method that prints the board in a 5x5 grid, board[i][j] == 1 represents the X's and board[i][j]==2 represents the O's
    //if board[i][j] = 1 then X is gonna be printed, if board[i][j] = 2 then O is gonna be printed
    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j]==1)
                    System.out.print("X ");
                else if (board[i][j]==2)
                    System.out.print("O ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
