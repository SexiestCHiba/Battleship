package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

public class Ship {

    private Pair<Integer, Integer> coords;
    private final int size;
    Pair<Integer,Integer>[] fullCoords;
    private Direction direction; // (0,-1) bas  // (0,1) haut  // (1,0) droite // (-1,0) gauche
    private boolean isDrown;

    public Ship(Pair<Integer, Integer> coords, int size, Direction direction) {
        this.coords = coords;
        this.size = size;
        this.fullCoords = new Pair[this.size];
        this.direction = direction;
        isDrown = false;
        recalculateFullCoords();
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

    public boolean isDrown(){
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

    public void recalculateFullCoords() {
        for(int i = 0; i < size; ++i){
            fullCoords[i] = new Pair<>(coords.getLeft() + i * direction.getDirection().getLeft(),coords.getRight() + i * direction.getDirection().getRight());
        }
    }

    public Pair<Integer, Integer>[] getFullCoords(){
        return fullCoords;
    }

    @Override
    public String toString() {
        return super.toString() + ", coords=" + coords.toString() + ", size=" + size + ", direction=" + direction.toString();
    }

    public void updateIsDrown(Player player) {
        int cpt = 0;
        for(Pair<Integer, Integer> coords : getFullCoords()) {
            for(Triplet<Integer,Integer,Boolean> move : player.getMoves()) {
                if(move.getRight() && move.getLeft().equals(coords.getLeft()) && move.getMiddle().equals(coords.getRight())){
                    ++cpt;
                    break;
                }
            }
        }
        if(cpt == getSize()) {
            setDrown();
        }
    }
}
