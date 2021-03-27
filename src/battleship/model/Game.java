package battleship.model;

import battleship.model.player.Player;

public class Game {

    public Player[] players;
    public Player currentPlayer;
    public int size;

    public Game(Player[] players,int size) {
        this.players = players;
        this.size = size;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void changeCurrentPlayer(){
        currentPlayer = (currentPlayer == players[1])? players[0] : players[1];
    }
    public void checkDrownedShips(){
        changeCurrentPlayer();
        Player otherPlayer = currentPlayer;
        changeCurrentPlayer();
        for(Ship ship : currentPlayer.getShips()){
            if(!ship.hasDrown())
                otherPlayer.isItDrown(ship);
        }
    }


}
