package battleship.view;

import battleship.control.TerminalKeyboardListener;
import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;

import java.util.Scanner;

public class Terminal extends AbstractView {

    public static Scanner scanner = null;
    private final TerminalKeyboardListener keyboardComponent;

    public Terminal(Game game) {
        super(game);
        if(scanner == null)
            scanner = new Scanner(System.in);
        keyboardComponent = new TerminalKeyboardListener(scanner);
    }

    @Override
    protected String getKeyInput() {
        return scanner.next().toUpperCase();
    }

    @Override
    protected void setUpperText(String s) {
        System.out.println(s);
    }

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

    @Override
    public void displayBoard() {
        System.out.println(this);
    }

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

    @Override
    protected Pair<Integer, Integer> mouseInput(Player player) {
        return null;
    }

    @Override
    protected String keyboardInput() {
        return keyboardComponent.keyboardInput();
    }

    protected int keyboardInputInteger() throws NumberFormatException {
        return Integer.parseInt(keyboardComponent.keyboardInput());
    }

    @Override
    public void displayWinner(Player winner) {
        displayBoard();
        setUpperText("Le joueur " + winner.getId() + " a gagné");
        scanner.close();
    }

}
