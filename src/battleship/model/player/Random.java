package battleship.model.player;

import battleship.utils.Pair;

public class Random extends Player{

    @Override
    public Pair<Integer,Integer> chooseMove() {
        Random rand = new Random();
        int index  = validMoves().get(rand.nextInt(validMoves().size()));
        return validMoves().get(index);
    }
}
