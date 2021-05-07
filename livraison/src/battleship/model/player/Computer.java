package battleship.model.player;

import battleship.model.Direction;
import battleship.model.Ship;
import battleship.utils.Pair;

import java.util.Random;

/**
 * <p>Computer super class, all player object which use an algorithm to calculate coordinates to use should extend from
 * this object.</p>
 * <p>Random is the only algorithm include here but another algorithm can be easily implemented</p>
 */
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
