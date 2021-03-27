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
        ArrayList<Triplet<Integer,Integer,Boolean>> player1 = game.players[0].getMoves();
        ArrayList<Triplet<Integer,Integer,Boolean>> player2 = game.players[1].getMoves();
        String chain = "";
        for(int i = 0; i<game.size;i++){
            for(int y = 0;y<game.size;y++){
                for(Triplet<Integer, Integer, Boolean> ships1 : player1){
                    if(i == ships1.getLeft()&& y == ships1.getMiddle()){
                        //chain +=
                    }
                }
                for(Triplet<Integer, Integer, Boolean> ships2 : player2){

                }
            }
        }
        // Not finished yet
        return chain;
    }
}
