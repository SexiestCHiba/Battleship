package battleship.view;

import battleship.model.Game;
import battleship.utils.Triplet;

import java.util.ArrayList;

public abstract class View {


    private final Game game;

    public View(Game game) {
        this.game = game;
    }
    @Override
    public String toString(){


        ArrayList<Triplet<Integer,Integer,Boolean>> player = game.currentPlayer.getMoves();
        String chain = "A vous de joueur "+game.currentPlayer+"\n+ - - - - - - - - - - +";
        for(int i = 0; i<10;i++){
            chain += "|";
            for(int y = 0;y<10;y++){
                for(Triplet<Integer, Integer, Boolean> ships : player){
                    if(i == ships.getLeft()&& y == ships.getMiddle()){
                        if(ships.getRight() == true){
                            chain += " !";
                        }
                        else
                            chain += " .";

                    }
                    else
                        chain += " ";
                }

            }
            chain += " |\n";
        }
        chain += "+ - - - - - - - - - - +\n";
        return chain;
    }
}
