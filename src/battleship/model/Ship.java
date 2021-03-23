package battleship.model;

import battleship.utils.Pair;

public class Ship {

    public Pair<Integer, Integer> coords;
    public int size;

    public Ship(Pair<Integer, Integer> coords, int size) {
        this.coords = coords;
        this.size = size;
    }

}
