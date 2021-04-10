package battleship.view;

import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

public abstract class AbstractView implements View{


    protected Game game;

    public AbstractView(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        // String chain = "A vous de joueur "+game.currentPlayer.toString()+ "\n+ - - - - - - - - - - +\n";
        String chain = "";
        for(int u = 0; u < 2; ++u) {
            Player player = game.players[u];
            ArrayList<Ship> ships = game.players[u].getShips();
            chain += "Joueur " + player.getId() + " :\n";
            chain += "+ - - - - - - - - - - +\n";
            for(int x = 0; x < 10; ++x) {
                chain += "|";
                for(int y = 0; y < 10; ++y) {
                    Pair<Integer, Integer> pair = new Pair<>(x, y);
                        boolean isPosition = false;
                        for(Ship ship : ships) {
                            if(isShipPosition(ship, pair)) {
                                isPosition = true;
                                int result = isPositionDrowned(game.players[u == 0 ? 1 : 0], ship, pair);
                                if(result == 1) {
                                    chain += " X";
                                } else if (result == 2){
                                    chain += " !";
                                } else {
                                    chain += " .";
                                }
                                break;
                            }
                        }
                        if(!isPosition) {
                            if(isPositionDrowned(game.players[u == 0 ? 1 : 0], pair) == 2) {
                                chain += " ?";
                            } else {
                                chain += " _";
                            }
                        }

                }
                chain += " |\n";
            }
            chain += "+ - - - - - - - - - - +\n";
        }

        return chain;
    }

    private boolean isShipPosition(Ship ship, Pair<Integer, Integer> boardsCoords) {
        for(Pair<Integer, Integer> coords : ship.getFullCoords()) {
            if(boardsCoords.equals(coords))
                return true;
        }
        return false;
    }

    @Override
    public void setShips(Player player) {
        player.placeShips();
    }

    /**
     *
     * @param other other than the current player
     * @param ship check if this ship at this position is touch
     * @param pair coords
     * @return 1 if ship fully drowned, 2 if only damaged, 0 if not
     */
    private int isPositionDrowned(Player other, Ship ship, Pair<Integer, Integer> pair) {
        if(ship.isDrown())
            return 1;
        return isPositionDrowned(other, pair);
    }

    private int isPositionDrowned(Player other, Pair<Integer, Integer> pair) {
        for(Triplet<Integer, Integer, Boolean> move : other.getMoves()) {
            if(pair.getLeft().equals(move.getLeft()) && pair.getRight().equals(move.getMiddle())) {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public Pair<Integer, Integer> chooseMove(Player player) {
        return player.chooseMove();
    }
}
