import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {
        AIplayer AI= new AIplayer();
        Board b = new Board();
        Point p = new Point(0, 0);
        Random rand = new Random();

        b.displayBoard();

        System.out.println("Who makes first move? (1)Computer (2)User: "); //Prints the statement between the quatation marks
        int choice = b.scan.nextInt(); //scans the input inserted which must be an int

        /* if I'm gonna insert 1 that means the computer will have the first move */
        if(choice == 1){
            AI.callMinimax(0, 1, b); //makes a call to the callMinimax function of depth 0 in the AIplayer class
            //iterates through rootsChildrenScores elements and prints the points and the scores
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            b.placeAMove(AI.returnBestMove(), 1);  //AI player places the move at the returned value (point) of the method returnBestMove()
            b.displayBoard();
        }

        //next turn (user's turn)
        while (!b.isGameOver()) {
            System.out.println("Your move: line (1, 2, 3, 4 or 5) colunm (1, 2, 3, 4 or 5)"); //prints the options
            Point userMove = new Point(b.scan.nextInt()-1, b.scan.nextInt()-1); // the inputed values are decreased by 1 and creates a point where the user should place a move on the grid.
            while (b.getState(userMove)!=0) { //if the move is invalid (the point is already occupied)
                System.out.println("Invalid move. Make your move again: ");
                //user inpus new values
                userMove.x=b.scan.nextInt()-1;  //value for x (line)
                userMove.y=b.scan.nextInt()-1; // value for y (column)
            }
            b.placeAMove(userMove, 2);  //User places a move at the point created above
            b.displayBoard();

            //if the game is over break the program
            if (b.isGameOver()) {
                break;
            }

            AI.callMinimax(0, 1, b);  //makes a call to the callMinimax function of depth 0 in the AIplayer class
            //iterates through rootsChildrenScores elements and prints the points and the scores
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            b.placeAMove(AI.returnBestMove(), 1);  //AI player places the move at the returned value (point) of the method returnBestMove()
            b.displayBoard();
        }

        //Prints the end of a game (if X(AI player) wins, O(the player) wins or it's a draw)
        if (b.hasXWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}