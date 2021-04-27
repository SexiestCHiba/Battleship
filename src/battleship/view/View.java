package battleship.view;

import battleship.model.player.Player;
import battleship.utils.Pair;

public interface View {

    int[] shipsSize = { 5, 4, 3, 3, 2};

    void setShips(Player player) throws InterruptedException;

    void displayBoard();

    Pair<Integer,Integer> chooseMove(Player player);

    void displayWinner(Player winner);
}
