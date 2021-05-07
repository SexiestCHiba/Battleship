package battleship.model.player;

import battleship.model.Ship;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import java.util.ArrayList;

/**
 * <p>Player interface, used as an API.</p>
 *  <p>This model (interface -> abstract class(es) -> concrete classes) prevent hard code.</p>
 *  <p>This is the only object which interact with other object<br>
 *  Allows an outside person from the project to easily change the code or to add a view easily without modifying
 *  existing classes</p>
 * @see AbstractPlayer
 * @see Computer
 * @see Human
 * @see Random
 */
public interface Player {

    int[] shipSize = { 5, 4, 3, 3, 2 };

    /**
     * Used by computer only
     * @see Random#chooseMove()
     * @return coords in its opponent grid to play a move
     */
    Pair<Integer,Integer> chooseMove();

    /**
     * check if ship position and direction are valides and does not overlap on other vessels
     * add the ship to player {@link AbstractPlayer#ships} list and return {@code true} if valid
     * {@code false} otherwise
     * @param ship the ship instance we check
     * @return {@code true} if ship data are valids, {@code false} otherwise
     */
    boolean setShips(Ship ship);

    int getId();

    /**
     * Adds coordinates of the {@code move} in the {@link AbstractPlayer#moves} list
     * @param move the move chosen by the player
     */
    void addMove(Triplet<Integer,Integer,Boolean> move);


    void setId(int i);

    /**
     * give a list of the player possible moves, used in {@link Player#chooseMove()}
     * @return a list of playable move
     */
    ArrayList<Pair<Integer,Integer>> validMoves();

    /**
     * Used by {@link Computer} instances to place ships
     */
    void placeShips();

    /**
     * check if coordinates from {@link battleship.view.View#chooseMove(Player)}are in valids position
     * @param x the x-axis of the coordinates
     * @param y the y-axis of the coordinates
     * @return {@code true} if valid, {@code false} otherwise
     */
    boolean areValid(int x, int y);

    ArrayList<Ship> getShips();

    ArrayList<Triplet<Integer,Integer,Boolean>> getMoves();
}
