package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.View;

public class Game {

    public Player[] players;
    public Player currentPlayer;

    public Game(Player[] players) {
        this.players = players;
        this.currentPlayer = players[0];
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void changeCurrentPlayer(){
        currentPlayer = (currentPlayer == players[1])? players[0] : players[1];
    }
    public void checkDrownedShips(){
        Player otherPlayer = (currentPlayer == players[1])? players[0] : players[1];
        for(Ship ship : currentPlayer.getShips()){
            if(!ship.hasDrown())
                otherPlayer.isItDrown(ship);
        }
    }
    public Player getWinner(){
        int cpt = 0;
        for(Ship ship : players[0].getShips()){
            if(!ship.hasDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[1];
        cpt = 0;
        for(Ship ship : players[1].getShips()){
            if(!ship.hasDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[0];
        return null;

    }
    public void move(Pair<Integer,Integer> move){
        boolean bool = false;
        Player player = (currentPlayer == players[1])? players[0] : players[1];
        for (Ship ship : player.getShips()) {
            for(Pair<Integer,Integer> pair : ship.getCoordsArray()){
                if ((pair.getRight().equals(move.getRight())) && (pair.getLeft().equals(move.getLeft()))) {
                    bool = true;
                    break;
                }
            }
        }
        currentPlayer.addMove(new Triplet<>(move.getLeft(),move.getRight(),bool));

    }
    public void setShips(Player player){
        //TODO a method that place the ships according to the decision of the players
    }
    public Player Play(View view){
        setShips(players[0]);
        setShips(players[1]);
        while(getWinner() == null){
            System.out.println(view);
            Pair<Integer,Integer> move  = currentPlayer.chooseMove();
            move(move);
            changeCurrentPlayer();
            checkDrownedShips();
        }
        return getWinner();
    }


}
