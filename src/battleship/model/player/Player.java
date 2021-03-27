package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Pair;
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
    public Boolean isItDrown(Ship ship){
        int cpt = 0;
        for(Triplet<Integer,Integer,Boolean> move : moves){
            for(int i =1;i<=ship.getSize();i++){
                int x = ship.getCoords().getLeft()+i*ship.getDirection().getLeft();
                int y = ship.getCoords().getRight()+i*ship.getDirection().getRight();
                if((move.getLeft() == x) && (move.getMiddle() == y)){
                    cpt += 1;
                    break;
                }
            }
        }
        if(cpt == ship.getSize()) {
            ship.setDrown();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public ArrayList<Ship> getShips(){
        return this.ships;
    }
    public ArrayList<Triplet<Integer,Integer,Boolean>> getMoves(){
        return this.moves;
    }

    public abstract Pair<Integer,Integer> chooseMove();

    public ArrayList<Pair<Integer,Integer>> validMoves(){
        ArrayList<Pair<Integer,Integer>> validMovesList = new ArrayList<>();
        for(Integer i = 0; i<10;i++){
            for(Integer y = 0;y<10;y++){
                if(!moves.contains(new Triplet<>(i,y,true)) ||!moves.contains(new Triplet<>(i,y,false))){
                    validMovesList.add(new Pair<>(i,y));
                }
            }
        }
        return null;

    }


}
