package battleship.view;

import battleship.model.player.Player;

public interface View {

    int[] shipsSize = { 5, 4, 3, 3, 2};

    void setShips(Player player);

    void displayBoard();

    void displayWinner(Player winner);
}
