package battleship.model.player;

import battleship.model.Direction;
import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

/**
 * Abstract player class see {@link Player} to know more about this code organisation
 * @see Player
 * @see Human
 * @see Computer
 * @see Random
 */
public abstract class AbstractPlayer implements Player {

    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<Triplet<Integer,Integer,Boolean>> moves = new ArrayList<>();
    public int id;

    public boolean setShips(Ship ship) {
        if(ship.getDirection() == Direction.DEFAULT)
            return false;
        for(Pair<Integer, Integer> coords : ship.getFullCoords()) {
            if(coords.getLeft() > 9 || coords.getLeft() < 0 || coords.getRight() > 9 || coords.getRight() < 0)
                return false;
            for(Ship ship1 : this.ships) {
                for (Pair<Integer, Integer> coords1 : ship1.getFullCoords()) {
                    if (coords1.getLeft().equals(coords.getLeft()) && coords1.getRight().equals(coords.getRight()))
                        return false;
                }
            }
        }
        this.ships.add(ship);
        return true;
    }

    /**
     * La methode retourne son objet afin d'avoir la possibilité de faire Player.addMove().addMove().etc...
     * @param move
     */
    public void addMove(Triplet<Integer,Integer,Boolean> move){
        moves.add(move);
    }

    public ArrayList<Pair<Integer,Integer>> validMoves() {
        ArrayList<Pair<Integer,Integer>> validMovesList = new ArrayList<>();
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++) {
                Pair<Integer, Integer> coords = new Pair<>(x,y);
                if(!(moves.contains(new Triplet<>(coords, true)) || moves.contains(new Triplet<>(coords, false)))){
                    validMovesList.add(new Pair<>(x,y));
                }
            }
        }
        return validMovesList;

    }

    public boolean areValid(int x, int y){
        if(x < 0 || x > 10 || y < 0 || y > 10)
            return false;
        for(Triplet<Integer,Integer,Boolean> move : moves){
            if(move.getLeft() == x && move.getMiddle() == y)
                return false;
        }
        return true;
    }

    @Override
    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    @Override
    public ArrayList<Ship> getShips() {
        return ships;
    }

    @Override
    public ArrayList<Triplet<Integer,Integer,Boolean>> getMoves() {
        return moves;
    }
}
