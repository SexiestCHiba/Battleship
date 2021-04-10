package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

public interface Player {

    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<Triplet<Integer,Integer,Boolean>> moves = new ArrayList<>();
    int[] ShipSize = { 5, 4, 3, 3, 2};

    Pair<Integer,Integer> chooseMove();

    boolean setShips(Ship ship);

    int getId();

    void updateIsDrown(Ship ship);

    Player addMove(Triplet<Integer,Integer,Boolean> move);




}
