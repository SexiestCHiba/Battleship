package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.View;

public class Game {

    public Player[] players;
    public Player currentPlayer;
    private final int[] ships = { 5, 4, 3, 3, 2};

    public Game(Player[] players) {
        this.players = players;
        this.currentPlayer = players[0];
        players[0].setId(1);
        players[1].setId(2);
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
            if(!ship.isDrown())
                otherPlayer.isItDrown(ship);
        }
    }

    public Player getWinner(){
        int cpt = 0;
        for(Ship ship : players[0].getShips()){
            if(!ship.isDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[1];
        cpt = 0;
        for(Ship ship : players[1].getShips()){
            if(!ship.isDrown())
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

    public Player Play(View view){
        view.setShips(players[0]);
        view.setShips(players[1]);
        System.out.println(view.toString());
        while(getWinner() == null) {
            System.out.println(view);
            Pair<Integer,Integer> move  = currentPlayer.chooseMove();
            move(move);
            changeCurrentPlayer();
            checkDrownedShips();
        }
        return getWinner();
    }


}
