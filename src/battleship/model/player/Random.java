package battleship.model.player;

import battleship.utils.Pair;

public class Random extends Player {

    @Override
    public Pair<Integer,Integer> chooseMove() {
        java.util.Random rand = new java.util.Random();
        // Pair<Integer, Integer> index  = validMoves().get(rand.nextInt(validMoves().size()));
        return validMoves().get(rand.nextInt(validMoves().size()));
    }
}
