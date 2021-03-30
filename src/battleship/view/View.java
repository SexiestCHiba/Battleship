package battleship.view;

import battleship.model.Game;
import battleship.model.player.Player;
import battleship.utils.Triplet;

import java.util.ArrayList;

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
        ArrayList<Triplet<Integer,Integer,Boolean>> moves = game.currentPlayer.getMoves();
        String chain = "A vous de joueur "+game.currentPlayer.toString()+ "\n+ - - - - - - - - - - +\n";
        for(int i = 0; i<10;i++){
            chain += "|";
            for(int y = 0;y<10;y++){
                if(!moves.isEmpty()) {
                    for (Triplet<Integer, Integer, Boolean> ships : moves) {
                        if (i == ships.getLeft() && y == ships.getMiddle()) {
                            if (ships.getRight())
                                chain += " !";
                            else
                                chain += " .";

                        }else
                            chain += " _";
                    }
                }else
                    chain += " _";
            }
            chain += " |\n";
        }
        chain += "+ - - - - - - - - - - +\n";
        return chain;
    }
    
}
