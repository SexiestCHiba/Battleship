package battleship.view;

import battleship.control.TerminalKeyboardListener;
import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;

import java.util.Scanner;

/**
 * Terminal view, instanced if argument 2 equals to "nogui"
 * @see View
 * @see AbstractView
 */
public class Terminal extends AbstractView {

    public static Scanner scanner = null;
    private final TerminalKeyboardListener keyboardComponent;

    public Terminal(Game game) {
        super(game);
        if(scanner == null)
            scanner = new Scanner(System.in);
        keyboardComponent = new TerminalKeyboardListener(scanner);
    }

    /**
     * @return given string in terminal
     */
    @Override
    protected String getKeyInput() {
        return scanner.next().toUpperCase();
    }

    /**
     * print string
     * @param s text to display
     */
    @Override
    protected void setUpperText(String s) {
        System.out.println(s);
    }

    /**
     * Ask {@code player} to set position of its ships
     * @param player player we ask to set position of its ships
     * @throws InterruptedException see {@link AbstractView#getDirectionFromChar()}
     */
    @Override
    public void setShips(Player player) throws InterruptedException {
        setUpperText("Joueur " + player.getId() + ", placez vos navires");
        int x, y;
        if(player instanceof Human) {
            for(int i : shipsSize) {
                boolean valid = false;
                Pair<Integer, Integer> defaultPos = new Pair<>(-1, -1);
                Ship ship = new Ship(defaultPos, i, Direction.DEFAULT);
                while (!player.setShips(ship)) {
                    try {
                        if (valid) {
                            setUpperText("Erreur");
                        }
                        valid = true;
                        setUpperText("Placement du bateau de longueur " + ship.getSize());
                        setUpperText("Veuillez indiquer la coordonée x de votre bateau");
                        x = keyboardInputInteger();
                        setUpperText("Veuillez indiquer la coordonée y de votre bateau");
                        y = keyboardInputInteger();
                        ship.setCoords(new Pair<>(x, y));
                        ship.setDirection(getDirectionFromChar());
                        ship.recalculateFullCoords();
                    } catch(NumberFormatException e) {
                        // Pour être sur qu'il ne passera pas la boucle
                        ship.setCoords(defaultPos);
                        ship.setDirection(Direction.DEFAULT);
                    }

                }
            }
        } else {
            super.setShips(player);
            // Computer
        }
    }

    /**
     * print board in terminal
     */
    @Override
    public void displayBoard() {
        System.out.println(this);
    }

    /**
     * ask player to choose a coords on its opponent grid, call {@link AbstractView#chooseMove(Player)} if instance of
     * player is {@link battleship.model.player.Computer}
     * if {@code player} isn't {@link Human} instance
     * @param player {@link battleship.model.Game#currentPlayer}
     * @return a element containing the x and y coordinate (left side store Y and right side X)
     * @throws InterruptedException see {@link AbstractView#chooseMove(Player)}
     */
    @Override
    public Pair<Integer, Integer> chooseMove(Player player) throws InterruptedException {
        if(player instanceof Human) {
            int x = -1, y = -1;
            while(!player.areValid(x, y)) {
                try {
                    // y correspond à l'ordonnée mais est stocké comme étant l'abscisse
                    // (erreur de notre part aperçu lors du passage à une fenetre swing)
                    setUpperText("Veuillez indiquer la coordonée x de votre coup");
                    y = keyboardInputInteger();
                    setUpperText("Veuillez indiquer la coordonée y de votre coup");
                    x = keyboardInputInteger();
                } catch (NumberFormatException ignored) {
                    x = -1;
                    y = -1;
                }
            }
            return new Pair<>(x,y);
        }
        return super.chooseMove(player);

    }

    /**
     * Never call in Terminal
     * @param player {@link Game#currentPlayer}
     * @return {@code null}
     */
    @Override
    protected Pair<Integer, Integer> mouseInput(Player player) {
        return null;
    }

    /**
     * @see TerminalKeyboardListener#keyboardInput()
     * @return given string in terminal
     */
    @Override
    protected String keyboardInput() {
        return keyboardComponent.keyboardInput();
    }

    /**
     * @see Terminal#keyboardInput()
     * @return convert string from keyboardInput() and convert it into an integer
     * @throws NumberFormatException if given string can't be parse into an integer
     */
    protected int keyboardInputInteger() throws NumberFormatException {
        return Integer.parseInt(keyboardComponent.keyboardInput());
    }

    /**
     * print grid, winner player and close scanner, game automatically close after this
     * @param winner the winner of the game.
     */
    @Override
    public void displayWinner(Player winner) {
        displayBoard();
        setUpperText("Le joueur " + winner.getId() + " a gagné");
        scanner.close();
    }

}
