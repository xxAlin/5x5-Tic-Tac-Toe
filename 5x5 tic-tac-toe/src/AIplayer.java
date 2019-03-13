import javax.naming.InsufficientResourcesException;
import java.util.*;

class AIplayer {
    List<Point> availablePoints;
    List<PointsAndScores> rootsChildrenScores;
    Board b = new Board();

    public AIplayer() {
    }

    //returns the best move the AI can make
    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) {
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }
        return rootsChildrenScores.get(best).point;
    }

    //finds the minimum
    public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    //finds the maximum
    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    //method which makes a call to the minimax method
    public void callMinimax(int depth, int turn, Board b) { //parameters
        rootsChildrenScores = new ArrayList<>();
        minimax(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, turn, b);
    } //alpha initial value = Integer.MIN_VALUE
      //beta initial value= Integer.MAX_VALUE

    public int minimax(int depth, int alpha, int beta, int turn, Board b) { //minimax parameters


        if (b.hasXWon()) return Integer.MAX_VALUE; //if X wins, return the maximum value in java
        if (b.hasOWon()) return Integer.MIN_VALUE; //if O wins, return the minimum value in java
        List<Point> pointsAvailable = b.getAvailablePoints(); //the list pointsAvailable which is equal to the method list getAvailablePoints() in the Board class
        if (pointsAvailable.isEmpty()) return 0; //if it's a draw returns 0
        if (depth == 6) { //limiting the depth to 6
            return b.heuristicTotal(); //returns the heuristics I made in Board.java
        }


            for (int i = 0; i < pointsAvailable.size(); ++i) {  //the for loop which goes up to the size of the size of the list called pointsAvailable
                Point point = pointsAvailable.get(i); //gets the element in the list for every i

                if (turn == 1) { //turn == 1 means is AI turn to make a move
                    b.placeAMove(point, 1); //AI player makes a move at the current point
                    int currentScore = minimax(depth + 1, alpha, beta, 2, b);  //int currentScore is the result of the recursive call, depth is increased by 1 and this goes to turn = 2
                    if (depth == 0) { //if the depth is 0, add the currentScore and point to the rootsChildrenScores list
                        rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                    }
                    if (currentScore > alpha) { //implementing of alpha beta pruning. if the currentScore > alpha, alpha will be equal to currentScore
                        alpha = currentScore;
                    }
                    if (alpha >= beta) { //if alpha >= beta
                        b.placeAMove(point, 0); //resets the point
                        return alpha;
                    }


                } else if (turn == 2) { //turn == 2 means is user's turn to place a move
                    b.placeAMove(point, 2); //User places a move at the current point
                    int currentScore2 = minimax(depth + 1,alpha ,beta, 1, b); //int currentScore2 is the result of the recursive call, depth is increased by 1 and this goes back to turn =1
                    if (currentScore2 < beta) { //if the currentScore2 < beta
                        beta = currentScore2; //beta takes the value of currentScore2
                    }
                    if (beta <= alpha){ //if beta <= alpha
                        b.placeAMove(point, 0); //resets the point
                        return beta;
                    }

                }

                b.placeAMove(point, 0); //if there is no player, it resets the point
            }

            return turn == 1 ? alpha : beta;
        }

    }
