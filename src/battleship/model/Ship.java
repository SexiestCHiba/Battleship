package battleship.model;

import battleship.utils.*;

public class Ship {

    private Pair<Integer, Integer> coords;
    private final int size;
    private Pair<Integer,Integer> direction;
    // (0,-1) bas  // (0,1) haut  // (1,0) droite // (-1,0) gauche
    private boolean isDrown;

    public Ship(Pair<Integer, Integer> coords, int size,Pair<Integer,Integer> direction) {
        this.coords = coords;
        this.size = size;
        this.direction = direction;
        isDrown = false;
    }
    public void setDirection(Pair<Integer,Integer> d){
        this.direction = d;
    }
    public void setCoords(Pair<Integer,Integer> c){
        this.coords = c;

    }

    public void setDrown(){
        isDrown = true;
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
    public Pair<Integer, Integer>[] getCoordsArray(){
        Pair<Integer,Integer>[]  tab = new Pair[size];
        for(int i = 0; i<size;i++){
            for(int y = 0;y<size;y++){
                tab[i] = new Pair<>(coords.getLeft()+i* direction.getLeft(),coords.getRight()+i* direction.getRight());
            }
        }
        return tab;


    }

}
