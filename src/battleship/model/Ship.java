package battleship.model;

import battleship.utils.Pair;

public class Ship {

    private Pair<Integer, Integer> coords;
    private final int size;
    private Direction direction; // (0,-1) bas  // (0,1) haut  // (1,0) droite // (-1,0) gauche
    private boolean isDrown;

    public Ship(Pair<Integer, Integer> coords, int size, Direction direction) {
        this.coords = coords;
        this.size = size;
        this.direction = direction;
        isDrown = false;
    }
    public void setDirection(Direction d){
        this.direction = d;
    }
    public void setCoords(Pair<Integer,Integer> c){
        this.coords = c;

    }

    public void setDrown(){
        isDrown = true;
    }

    public Boolean isDrown(){
        return isDrown;
    }

    public int getSize(){
        return this.size;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public Pair<Integer,Integer> getCoords(){
        return this.coords;
    }

    @SuppressWarnings("unchecked")
    public Pair<Integer, Integer>[] getCoordsArray(){
        Pair<Integer,Integer>[] tab = new Pair[size];
        for(int i = 0; i<size;i++){
            for(int y = 0;y<size;y++){
                tab[i] = new Pair<>(coords.getLeft()+i* direction.getDirection().getLeft(),coords.getRight()+i * direction.getDirection().getRight());
            }
        }
        return tab;


    }

    @Override
    public String toString() {
        return super.toString() + ", coords=" + coords.toString() + ", size=" + size + ", direction=" + direction.toString();
    }
}
