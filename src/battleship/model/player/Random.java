package battleship.model.player;

import battleship.utils.Pair;

public class Random extends AbstractPlayer {

    @Override
    public Pair<Integer,Integer> chooseMove() {
        java.util.Random rand = new java.util.Random();
        return validMoves().get(rand.nextInt(validMoves().size()));
    }
    @Override
    public String toString(){
        return "Random " +id;
    }
}
