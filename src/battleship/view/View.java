package battleship.view;

import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.awt.Graphics;
import java.awt.*;
import java.util.ArrayList;

public abstract class View {


    protected final Game game;
    protected final int[] ships = { 5, 4, 3, 3, 2};

    public View(Game game) {
        this.game = game;
    }

    public abstract void setShips(Player player);

    public abstract void displayBoard();

    @Override
    public String toString() {
        // String chain = "A vous de joueur "+game.currentPlayer.toString()+ "\n+ - - - - - - - - - - +\n";
        String chain = "";
        for(int u = 0; u < 2; ++u) {
            Player player = game.players[u];
            ArrayList<Ship> ships = game.players[u].getShips();
            chain += "Player " + (u + 1) + " :\n";
            chain += "+ - - - - - - - - - - +\n";
            for(int x = 0; x < 10; ++x) {
                chain += "|";
                for(int y = 0; y < 10; ++y) {
                    Pair<Integer, Integer> pair = new Pair<>(x, y);
                        boolean isPosition = false;
                        for(Ship ship : ships) {
                            if(isShipPosition(ship, pair)) {
                                isPosition = true;
                                if(isPositionDrowned(game.players[u == 0 ? 1 : 0], ship, pair)) {
                                    chain += " !";
                                } else {
                                    chain += " .";
                                }
                                break;
                            }
                        }
                        if(!isPosition)
                            chain += " _";
                }
                chain += " |\n";
            }
            chain += "+ - - - - - - - - - - +\n";
        }

        return chain;
    }

    private boolean isShipPosition(Ship ship, Pair<Integer, Integer> boardsCoords) {
        if(ship.getCoords().equals(boardsCoords))
            return true;
        for(int a = 0; a < ship.getSize(); ++a) {
            if(new Pair<>(ship.getCoords().getLeft() + a * ship.getDirection().getDirection().getLeft(), ship.getCoords().getRight() + a * ship.getDirection().getDirection().getRight()).equals(boardsCoords)) {
                return true;
            }
        }
        return false;
    }


    private boolean isPositionDrowned(Player other, Pair<Integer, Integer> pair) {
        for(Triplet<Integer, Integer, Boolean> move : other.getMoves()) {
            if(move.getRight() && pair.getLeft().equals(move.getLeft()) && pair.getRight().equals(move.getMiddle())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositionDrowned(Player other, Ship ship, Pair<Integer, Integer> pair) {
        if(ship.isDrown())
            return true;
        return isPositionDrowned(other, pair);
    }
    
}
