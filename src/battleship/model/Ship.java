package battleship.model;

import battleship.utils.*;

public class Ship {

    private final Pair<Integer, Integer> coords;
    private final int size;
    private final Pair<Integer,Integer> direction;
    // (0,-1) bas  // (0,1) haut  // (1,0) droite // (-1,0) gauche
    private Boolean isDrown;

    public Ship(Pair<Integer, Integer> coords, int size,Pair<Integer,Integer> direction) {
        this.coords = coords;
        this.size = size;
        this.direction = direction;
        this.isDrown = Boolean.FALSE;
    }
    public void isDrown(){
        isDrown = Boolean.TRUE;
    }
    public Boolean hasDrown(){
        return isDrown;
    }
    public int getSize(){
        return this.size;
    }
    public Pair<Integer, Integer> getDirection(){
        return this.direction;
    }
    public Pair<Integer,Integer> getCoords(){
        return this.coords;
    }

}
