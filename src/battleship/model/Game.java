package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.View;

/**
 * Main game class
 */
public class Game {

    public Player[] players;
    public Player currentPlayer;

    public Game(Player[] players) {
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

    public Player getOtherPlayer(Player player) {
        return this.currentPlayer == player ? getOtherPlayer() : currentPlayer;
    }

    public void changeCurrentPlayer(){
        currentPlayer = getOtherPlayer();
    }

    /**
     * Update ship to know if they're drowned
     */
    public void checkDrownedShips(){
        Player otherPlayer = getOtherPlayer();
        for(Ship ship : otherPlayer.getShips()){
            if(!ship.isDrown())
                ship.updateIsDrown(currentPlayer);
        }
    }

    /**
     * @return player 1 if player 2' ships are drowned, or player 2 if player1' ships are drowned, null otherwise
     */
    public Player getWinner(){
        Ship remainingShip = players[0].getShips().parallelStream().filter(ship -> !ship.isDrown()).findFirst().orElse(null);
        if(remainingShip == null)
            return players[1];
        remainingShip = players[1].getShips().parallelStream().filter(ship -> !ship.isDrown()).findFirst().orElse(null);
        if(remainingShip == null)
            return players[0];
        return null;

    }

    /**
     * Play the selected move from current player in grid
     * @param move selected player move
     */
    public void move(Pair<Integer,Integer> move){
        boolean bool = false;
        Player otherPlayer = getOtherPlayer();
        for (Ship ship : otherPlayer.getShips()) {
            for(Pair<Integer,Integer> coords : ship.getFullCoords()){
                if ((coords.getRight().equals(move.getRight())) && (coords.getLeft().equals(move.getLeft()))) {
                    bool = true;
                    break;
                }
            }
            if(bool)
                break;
        }
        currentPlayer.addMove(new Triplet<>(move, bool));
    }

    /**
     * game loop
     * @param view can be {@link battleship.view.Terminal} or {@link battleship.view.Window}
     */
    public void Play(View view) {
        try {
            view.setShips(players[0]);
            view.setShips(players[1]);
            Player winner = null;
            while (winner == null) {
                System.out.println("Au tour du joueur " + currentPlayer.getId());
                view.displayBoard();
                move(view.chooseMove(currentPlayer));
                checkDrownedShips();
                changeCurrentPlayer();
                winner = getWinner();
            }
            view.displayWinner(winner);
        } catch (InterruptedException e) {
            System.out.println("Une erreur est survenue");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
