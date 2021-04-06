package battleship.view;

import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class View {


    protected final Game game;
    protected final int[] ships = { 5, 4, 3, 3, 2};

    public View(Game game) {
        this.game = game;
    }

    public abstract void setShips(Player player);

    public abstract void displayBoard();

    protected void placeShipRandomly(Player player) {
        Random rand = new Random();
        for(int i : ships) {
            Ship ship = null;
            while(ship == null || !player.setShips(ship)) {
                ship = new Ship(new Pair<>(rand.nextInt(10), rand.nextInt(10)), i, Direction.values()[rand.nextInt(Direction.values().length)]);
            }
        }
    }

    @Override
    public String toString() {
        ArrayList<Triplet<Integer,Integer,Boolean>> moves = game.currentPlayer.getMoves();
        String chain = "A vous de joueur "+game.currentPlayer.toString()+ "\n+ - - - - - - - - - - +\n";
        
        for(AtomicInteger i = new AtomicInteger(0); i.get() < 10;i.incrementAndGet()) {
            chain += "|";
            for(AtomicInteger y = new AtomicInteger(0);y.get() < 10; y.incrementAndGet()) {
                if(!moves.isEmpty()) {
                	Triplet<Integer, Integer, Boolean> move = moves.stream().filter(p -> p.getLeft() == i.get() && p.getMiddle() == y.get()).findFirst().orElse(null);
                	if(move != null) {
                		if (move.getRight())
                            chain += " !";
                        else
                            chain += " .";
                	} else {
                		chain += " _";
                	}
                }else {
                	chain += " _";
                }
            }
            chain += " |\n";
        }
        chain += "+ - - - - - - - - - - +\n";
        return chain;
    }
    
}
