package battleship.model.player;

import battleship.utils.Triplet;

import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Ships> ships = new ArrayList<>();
    protected ArrayList<Triplet> moves = new ArrayList<>();

    public Player(){
        setShips();
    }
    public void setShips(){

    }
    public void addMove(Triplet move){
        moves.add(move);
    }
    public abstract Triplet chooseMove();
}
