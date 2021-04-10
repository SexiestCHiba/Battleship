package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

public interface Player {

    int[] shipSize = { 5, 4, 3, 3, 2 };

    Pair<Integer,Integer> chooseMove();

    boolean setShips(Ship ship);

    int getId();

    Player addMove(Triplet<Integer,Integer,Boolean> move);


    void setId(int i);

    ArrayList<Pair<Integer,Integer>> validMoves();

    void placeShips();

    boolean areValid(int x, int y);

    ArrayList<Ship> getShips();

    ArrayList<Triplet<Integer,Integer,Boolean>> getMoves();
}
