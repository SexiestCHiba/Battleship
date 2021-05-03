package battleship.model.player;

import battleship.utils.Pair;

/**
 * place its ship and choose moves randomly
 */
public class Random extends Computer {

    @Override
    public Pair<Integer,Integer> chooseMove() {
        java.util.Random rand = new java.util.Random();
        return validMoves().get(rand.nextInt(validMoves().size()));
    }

    @Override
    public void placeShips() {
        placeShipRandomly();
    }

}
