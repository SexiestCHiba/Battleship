package battleship.view;

import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

/**
 *  Abstract view class
 * @see View
 * @see Window
 * @see Terminal
 */
public abstract class AbstractView implements View {

    protected Game game;

    public AbstractView(Game game) {
        this.game = game;
    }

    /**
     * Used during debugging, used in terminal to display grids too
     * @return all player grids
     */
    @Override
    public String toString() {
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

    /**
     *
     * @return {@code true} if {@link Ship#getFullCoords()} contains {@code boardsCoords}, {@code false} otherwise
     */
    private boolean isShipPosition(Ship ship, Pair<Integer, Integer> boardsCoords) {
        for(Pair<Integer, Integer> coords : ship.getFullCoords()) {
            if(boardsCoords.equals(coords))
                return true;
        }
        return false;
    }

    /**
     * ask player for keyboard input and parse it into one of {@link Direction} value
     * @return Direction depending of player input
     * @throws InterruptedException see {@link Window#getDirectionFromChar()}
     * @see Window#getDirectionFromChar()
     */
    protected Direction getDirectionFromChar() throws InterruptedException {
        String dir;
        while (true) {
            setUpperText("Veuillez indiquer la direction de placement de votre bateau (d droite, h haut, b bas, g gauche)");
            dir = getKeyInput();
            for (Direction direction : Direction.values()) {
                if (direction.getKeyword() != null && direction.getKeyword().equals(dir)) {
                    return direction;
                }
            }
        }
    }

    /**
     * ask player for keyboard input and return result
     * @return String given by player
     * @throws InterruptedException see {@link Window#getKeyInput()}
     */
    protected abstract String getKeyInput() throws InterruptedException;

    /**
     * Display a text above the grid on {@link Window}, simply print text on {@link Terminal}
     * @param s text to display
     * @see Window#setUpperText(String) 
     * @see Terminal#setUpperText(String)
     */
    protected abstract void setUpperText(String s);

    /**
     * used if {@code player} instance of {@link battleship.model.player.Computer}
     * @param player player we ask to set position of its ships
     * @throws InterruptedException see {@link Window#setShips(Player)}
     */
    @Override
    public void setShips(Player player) throws InterruptedException {
        player.placeShips();
    }

    /**
     * @param other other than the current player
     * @param ship check if this ship at this position has been hit
     * @param pair coords
     * @return 1 if ship fully drowned, 2 if only damaged, 0 if not
     * @see
     */
    private int isPositionDrowned(Player other, Ship ship, Pair<Integer, Integer> pair) {
        if(ship.isDrown())
            return 1;
        return isPositionDrowned(other, pair);
    }

    /**
     * @param other other than the current player
     * @param pair coords to check
     * @return 2 if player already played here, 0 otherwise
     */
    private int isPositionDrowned(Player other, Pair<Integer, Integer> pair) {
        for(Triplet<Integer, Integer, Boolean> move : other.getMoves()) {
            if(pair.getLeft().equals(move.getLeft()) && pair.getRight().equals(move.getMiddle())) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * used by {@link battleship.model.player.Computer} player to play a move in the grid depending of its algorithm
     * @param player {@link battleship.model.Game#currentPlayer}
     * @return a couple ({@link Pair} containing the x and y coordinate (left side store Y and right side X)
     * @throws InterruptedException see {@link Window#chooseMove(Player)}
     */
    @Override
    public Pair<Integer, Integer> chooseMove(Player player) throws InterruptedException {
        return player.chooseMove();
    }

    /**
     * ask {@code player} for mouse input
     * @param player {@link Game#currentPlayer}
     * @return coordinate of {@code player} opponent grid
     * @see Window#mouseInput(Player)
     * @throws InterruptedException see {@link Window#mouseInput(Player)}
     */
    protected abstract Pair<Integer, Integer> mouseInput(Player player) throws InterruptedException;

    /**
     * ask {@link Game#currentPlayer} for keyboard input
     * @return String given by player
     * @throws InterruptedException see {@link Window#keyboardInput()}
     * @see Window#keyboardInput()
     * @see Terminal#keyboardInput()
     * @see Terminal#keyboardInputInteger()
     */
    protected abstract String keyboardInput() throws InterruptedException;
}
