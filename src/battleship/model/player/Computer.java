package battleship.model.player;

import battleship.model.Direction;
import battleship.model.Ship;
import battleship.utils.Pair;

import java.util.Random;

public abstract class Computer extends AbstractPlayer {

    public void placeShipRandomly() {
        java.util.Random rand = new Random();
        for(int i : shipSize) {
            Ship ship = new Ship(new Pair<>(-1, -1), i, Direction.DEFAULT);
            while(!setShips(ship)) {
                ship.setCoords(new Pair<>(rand.nextInt(10), rand.nextInt(10)));
                ship.setDirection(Direction.values()[rand.nextInt(Direction.values().length)]);
                ship.recalculateFullCoords();
            }
        }
    }

}
