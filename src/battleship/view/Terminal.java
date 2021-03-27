package battleship.view;

import battleship.model.Game;
import battleship.utils.Triplet;

import java.util.ArrayList;

public class Terminal extends View {


    public Terminal(Game game) {
        super(game);
    }

    @Override
    public String toString(){
        ArrayList<Triplet<Integer,Integer,Boolean>> moves = game.currentPlayer.getMoves();
        String chain = "A vous de joueur "+game.currentPlayer+"\n+ - - - - - - - - - - +\n";
        for(int i = 0; i<10;i++){
            chain += "|";
            for(int y = 0;y<10;y++){
                if(moves.isEmpty())
                    chain += " _";
                else {
                    for (Triplet<Integer, Integer, Boolean> ships : moves) {
                        if (i == ships.getLeft() && y == ships.getMiddle()) {
                            if (ships.getRight() == true)
                                chain += " !";
                            else
                                chain += " .";

                        } else
                            chain += " _";
                    }
                }

            }
            chain += " |\n";
        }
        chain += "+ - - - - - - - - - - +\n";
        return chain;
    }

}
