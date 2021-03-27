package battleship.model.player;

import battleship.utils.Pair;

public class Random extends Player {

    @Override
    public Pair<Integer,Integer> chooseMove() {
        java.util.Random rand = new java.util.Random();
        return validMoves().get(rand.nextInt(validMoves().size()));
    }
}
