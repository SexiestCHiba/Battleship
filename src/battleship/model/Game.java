package battleship.model;

import battleship.model.player.AbstractPlayer;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.AbstractView;

import java.util.Random;

public class Game {

    public Player[] players;
    public Player currentPlayer;

    public Game(AbstractPlayer[] players) {
        this.players = players;
        this.currentPlayer = players[0];
        players[0].setId(1);
        players[1].setId(2);
    }


    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public Player getOtherPlayer() {
        return this.currentPlayer == players[0] ? players[1] : players[0];
    }

    public void changeCurrentPlayer(){
        currentPlayer = getOtherPlayer();
    }

    public void checkDrownedShips(){
        Player otherPlayer = getOtherPlayer();
        for(Ship ship : currentPlayer.ships){
            if(!ship.isDrown())
                otherPlayer.updateIsDrown(ship);
        }
    }

    public Player getWinner(){
        Ship remainingShip = players[0].ships.parallelStream().filter(ship -> !ship.isDrown()).findFirst().orElse(null);
        if(remainingShip == null)
            return players[1];
        remainingShip = players[1].ships.parallelStream().filter(ship -> !ship.isDrown()).findFirst().orElse(null);
        if(remainingShip == null)
            return players[1];
        return null;

    }
    public void move(Pair<Integer,Integer> move){
        boolean bool = false;
        Player otherPlayer = getOtherPlayer();
        for (Ship ship : otherPlayer.ships) {
            for(Pair<Integer,Integer> pair : ship.getCoordsArray()){
                if ((pair.getRight().equals(move.getRight())) && (pair.getLeft().equals(move.getLeft()))) {
                    bool = true;
                    break;
                }
            }
        }
        currentPlayer.addMove(new Triplet<>(move, bool));
    }

    public void Play(AbstractView view){
        view.setShips(players[0]);
        view.setShips(players[1]);
        Player winner = null;
        while(winner == null) {
            view.displayBoard();
            move(currentPlayer.chooseMove());
            changeCurrentPlayer();
            checkDrownedShips();
            winner = getWinner();
        }
        view.displayWinner(winner);
    }


    public void placeShipRandomly(Player player) {
        Random rand = new Random();
        for(int i : player.ShipSize) {
            Ship ship = new Ship(new Pair<>(-1, -1), i, Direction.DEFAULT);
            while(!player.setShips(ship)) {
                ship = new Ship(new Pair<>(rand.nextInt(10), rand.nextInt(10)), i, Direction.values()[rand.nextInt(Direction.values().length)]);
            }
        }
    }

}
