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

    @Override
    public String toString() {
        // String chain = "A vous de joueur "+game.currentPlayer.toString()+ "\n+ - - - - - - - - - - +\n";
        String chain = "";
        for(int u = 0; u < 2; ++u) {
            ArrayList<Triplet<Integer,Integer,Boolean>> moves = game.players[u].getMoves();
            chain += "Player " + (u + 1) + " :\n";
            chain += "+ - - - - - - - - - - +\n";
            for(int i = 0; i < 10;++i) {
                chain += "|";
                for(int y = 0;y < 10; ++y) {
                    if(!moves.isEmpty()) {
                        for(Triplet<Integer, Integer, Boolean> move : moves) {
                            if(move.getLeft() == i && move.getMiddle() == y) {
                                if (move.getRight())
                                    chain += " !";
                                else
                                    chain += " .";
                            } else {
                                chain += " _";
                            }
                            break;
                        }
                    } else {
                        chain += " _";
                    }
                }
                chain += " |\n";
            }
            chain += "+ - - - - - - - - - - +\n";
        }

        return chain;
    }
    
}
