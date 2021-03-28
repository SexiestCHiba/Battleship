package battleship.view;

import battleship.model.Game;
import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

public class Terminal extends View {


    public Terminal(Game game) {
        super(game);
    }

    public String setShip(Ship ship, int x, int y, Pair<Integer,Integer> direction){
        String chain = "+ - - - - - - - - - - +\n";
        return chain;
    }

    @Override
    public String toString(){
        ArrayList<Triplet<Integer,Integer,Boolean>> moves = game.currentPlayer.getMoves();
        String chain = "A vous de joueur "+game.currentPlayer.toString()+"\n+ - - - - - - - - - - +\n";
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
