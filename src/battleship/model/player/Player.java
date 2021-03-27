package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Triplet;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player {

    protected ArrayList<Ship> ships = new ArrayList<>();
    protected ArrayList<Triplet<Integer,Integer,Boolean>> moves = new ArrayList<>();

    public Player(){
        setShips();
    }

    public void setShips(Ship... ships){
        this.ships.addAll(Arrays.asList(ships));
    }

    /**
     * La methode retourne son objet afin d'avoir la possibilité de faire Player.addMove().addMove().etc...
     * @param move
     * @return Player
     */
    public Player addMove(Triplet<Integer,Integer,Boolean> move){
        moves.add(move);
        return this;
    }

    public abstract Triplet<Integer,Integer,Boolean> chooseMove();
}
