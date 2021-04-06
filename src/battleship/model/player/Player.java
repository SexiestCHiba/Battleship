package battleship.model.player;

import battleship.model.Direction;
import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player {

    protected ArrayList<Ship> ships = new ArrayList<>();
    protected ArrayList<Triplet<Integer,Integer,Boolean>> moves = new ArrayList<>();
    protected int id;
    protected final static int[] bato = { 5, 4, 3, 3, 2};

    public boolean setShips(Ship ship) {
        for(int i = 0; i < ship.getSize(); i++){
            int x = ship.getCoords().getLeft() + i * ship.getDirection().getDirection().getLeft();
            int y = ship.getCoords().getRight()+ i * ship.getDirection().getDirection().getRight();
            if(x > 9 || x < 0 || y > 9 || y < 0)
                return false;
            for(Ship ship1 : this.ships) {
                for (int j = 0; j < ship1.getSize(); j++) {
                    int x1 = ship1.getCoords().getLeft() + i * ship1.getDirection().getDirection().getLeft();
                    int y1 = ship1.getCoords().getRight() + i * ship1.getDirection().getDirection().getRight();
                    if (x1 == x && y1 == y)
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
     * @return Player
     */
    public Player addMove(Triplet<Integer,Integer,Boolean> move){
        moves.add(move);
        return this;
    }

    public boolean isItDrown(Ship ship) {
        int cpt = 0;
        for(Triplet<Integer,Integer,Boolean> move : moves){
            for(int i = 1; i <= ship.getSize(); i++){
                int x = ship.getCoords().getLeft()+ i * ship.getDirection().getDirection().getLeft();
                int y = ship.getCoords().getRight()+ i * ship.getDirection().getDirection().getRight();
                if((move.getLeft() == x) && (move.getMiddle() == y)){
                    cpt += 1;
                    break;
                }
            }
        }
        if(cpt == ship.getSize()) {
            ship.setDrown();
            return true;
        }
        return false;
    }
    public ArrayList<Ship> getShips(){
        return this.ships;
    }

    public ArrayList<Triplet<Integer,Integer,Boolean>> getMoves(){
        return this.moves;
    }

    public abstract Pair<Integer,Integer> chooseMove();

    public ArrayList<Pair<Integer,Integer>> validMoves() {
        ArrayList<Pair<Integer,Integer>> validMovesList = new ArrayList<>();
        for(Integer i = 0; i<10;i++){
            for(Integer y = 0;y<10;y++) {
                if(!moves.contains(new Triplet<>(i,y,true)) ||!moves.contains(new Triplet<>(i,y,false))){
                    validMovesList.add(new Pair<>(i,y));
                }
            }
        }
        return validMovesList;

    }
    public void setId(int i){
        id = i;
    }

    public int getId() {
        return id;
    }
    
    public void placeShipRandomly() {
        Random rand = new Random();
        for(int i : bato) {
            Ship ship = new Ship(new Pair<>(-1, -1), i, Direction.DEFAULT);
            while(!setShips(ship)) {
                ship = new Ship(new Pair<>(rand.nextInt(10), rand.nextInt(10)), i, Direction.values()[rand.nextInt(Direction.values().length)]);
            }
        }
    }
}
