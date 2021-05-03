package battleship.model.player;

import battleship.utils.Pair;

/**
 * This object do nothing itself, it just an interface to know the type of player (Human or not),
 * each view interact with the player with its methods
 */
public class Human extends AbstractPlayer {

    @Override
    public Pair<Integer,Integer> chooseMove() {
        return null;
    }

    @Override
    public void placeShips() {}

}
