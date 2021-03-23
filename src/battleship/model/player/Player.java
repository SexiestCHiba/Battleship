package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Triplet;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Ship> ships = new ArrayList<>();
    protected ArrayList<Triplet<Integer,Integer,Boolean>> moves = new ArrayList<>();

    public Player(){
        setShips();
    }

    public void setShips(){

    }

    public void addMove(Triplet<Integer,Integer,Boolean> move){
        moves.add(move);
    }
    public abstract Triplet<Integer,Integer,Boolean> chooseMove();
}
