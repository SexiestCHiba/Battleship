package battleship.model;

import battleship.model.player.Player;

public class Game {

    public Player[] players;
    public Player currentPlayer;

    public Game(Player[] players) {
        this.players = players;
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


}
