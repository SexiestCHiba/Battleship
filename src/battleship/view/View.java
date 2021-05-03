package battleship.view;

import battleship.model.player.Player;
import battleship.utils.Pair;

/**
 * <p>View interface, used as an API</p>
 * <p>This model (interface -> abstract class(es) -> concrete classes) prevent hard code.</p>
 * <p>This is the only object which interact with other object<br>
 * Allows an outside person easily change the code or add a view easily without modifying existing classes</p>
 * @see AbstractView
 * @see Terminal
 * @see Window
 */
public interface View {

    int[] shipsSize = { 5, 4, 3, 3, 2};

    /**
     * Ask {@code player} to set position of its ships
     * @param player player instance we ask
     * @throws InterruptedException see {@link Window#setShips(Player)}
     */
    void setShips(Player player) throws InterruptedException;

    /**
     * Display all grids
     */
    void displayBoard();

    /**
     * ask the player the choose a position on its opponent grid
     * @param player {@link battleship.model.Game#currentPlayer}
     * @return a element containing the x and y coordinate (left side store Y and right side X)
     * @throws InterruptedException see {@link Window#chooseMove(Player)}
     */
    Pair<Integer,Integer> chooseMove(Player player) throws InterruptedException;

    /**
     * Display the winner of the game and then close the game
     * @param winner the winner of the game.
     * @see Window#displayWinner(Player)
     * @see Terminal#displayWinner(Player)
     */
    void displayWinner(Player winner);
}
