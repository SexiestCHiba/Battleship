package battleship.view;

import battleship.control.WindowKeyboardListener;
import battleship.control.WindowMouseListener;
import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends AbstractView {

    final JFrame frame;

    public final int height = 600;
    public final int width = 1200;
    private final WindowMouseListener mouseComponent;
    private final WindowKeyboardListener keyboardComponent;
    String upperTitle = "";
    String upperSubTitle = "";

    public Window(Game game) {
        super(game);
        this.frame = new JFrame("Battleship");
        frame.setSize(width + width / 13, height + height / 4);
        frame.setResizable(false);
        frame.setContentPane(new Draw(this));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.mouseComponent = new WindowMouseListener(this);
        frame.addMouseListener(mouseComponent);
        this.keyboardComponent = new WindowKeyboardListener(this);
        frame.addKeyListener(keyboardComponent);

    }

    @Override
    protected String getKeyInput() throws InterruptedException {
        return waitingForKeyboardInput();
    }

    @Override
    protected void setUpperText(String s) {
        upperTitle = s;
    }

    @Override
    public void setShips(Player player) throws InterruptedException {
        if(player instanceof Human) {
            for(int i : shipsSize) {
                Ship ship = new Ship(new Pair<>(-1, -1), i, Direction.DEFAULT);
                boolean valid = false;
                while(!player.setShips(ship)) {
                    frame.repaint();
                    if(valid)
                        openDialog("Erreur de placement, votre navire se superpose avec un autre, ou la direction donnée n'est pas valide");

                    upperTitle = "joueur " + player.getId() + ", Placez votre premier navire de taille " + i + " à l'aide de la souris";
                    ship.setCoords(waitingForMouseInput(player));
                    upperTitle = "joueur " + player.getId() + ", Choisissez la direction de votre navire avec le clavier";
                    upperSubTitle = "H, B, G, D pour respectivement Haut, Bas, Gauche, Droite";
                    frame.repaint();
                    ship.setDirection(getDirectionFromChar());
                    ship.recalculateFullCoords();
                    valid = true;
                }

            }
            upperTitle = "";
            upperSubTitle = "";
        } else {
            super.setShips(player);
        }
    }

    private String waitingForKeyboardInput() throws InterruptedException {
        keyboardComponent.requestInput = true;
        while(true) {
            Thread.sleep(32);
            if(keyboardComponent.keyTyped != KeyEvent.CHAR_UNDEFINED) {
                keyboardComponent.requestInput = false;
                String value = String.valueOf(keyboardComponent.keyTyped).toUpperCase();
                keyboardComponent.keyTyped = KeyEvent.CHAR_UNDEFINED;
               return value;
            }
        }
    }

    private Pair<Integer, Integer> waitingForMouseInput(Player player) throws InterruptedException {
        mouseComponent.requestInput = true;
        while(true) {
            Thread.sleep(32);
            if(mouseComponent.playerIdLastInput != 0) {
                if(player.getId() == mouseComponent.playerIdLastInput) {
                    mouseComponent.requestInput = false;
                    mouseComponent.playerIdLastInput = 0;
                    Pair<Integer, Integer> value = mouseComponent.lastInput;
                    mouseComponent.lastInput = null;
                    System.out.println(value);
                    return value;
                } else {
                    openDialog("Vous avez cliquer sur une zone de jeu qui n'est pas la votre");
                    mouseComponent.playerIdLastInput = 0;
                }
            }

        }
    }

    public void openDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    @Override
	public void displayBoard() {
        frame.paintComponents(frame.getGraphics());
	}

    @Override
    public Pair<Integer, Integer> chooseMove(Player player) {

        return null;
    }

    @Override
    public void displayWinner(Player winner) {
        // TODO: 07/04/2021 afficher un dialog
    }

    class Draw extends JPanel {

        private final Window window;

        public Draw(Window window) {
            this.window = window;
        }

        public void paintComponent(Graphics g) {
            g.drawString(upperTitle, (int) (window.width /2 - (upperTitle.length() * 2.5)), 50);
            g.drawString(upperSubTitle, (int) (window.width / 2 -  (upperSubTitle.length() * 2.5)), 65);
            int width = window.width;
            int height = window.height;
            int initialHeight = height / 12;
            int initialWidth = width / 23;
            for(int abscisse = initialWidth; abscisse < width; abscisse += initialWidth) {
                g.drawLine(abscisse, initialHeight * 2, abscisse, height);
                if(abscisse == initialWidth * 11)
                    abscisse += initialWidth;
            }
            for(int ordonnee = initialHeight * 2; ordonnee < height + 1; ordonnee += initialHeight) {
                g.drawLine(initialWidth, ordonnee, initialWidth * 11, ordonnee);
                g.drawLine(initialWidth * 13, ordonnee, width - 4, ordonnee);
            }

            for(int i = 1; i < 3; ++i) {
                Player player = game.players[i-1];
                for(Ship ship : player.getShips()) {
                    int x1 = i == 1 ? initialWidth : initialWidth * 13;
                    int y1 = initialHeight * 2;
                    int shipWidth = initialWidth;
                    int shipHeight = initialHeight;
                    System.out.println(ship);
                    switch(ship.getDirection()) {
                        case DOWN:
                            x1 += initialWidth * ship.getCoords().getRight();
                            y1 += initialHeight * ship.getCoords().getLeft();
                            shipHeight = initialHeight * ship.getSize();
                            g.setColor(new Color(255, 0, 0));
                            break;
                        case UP:
                            x1 += initialWidth * ship.getCoords().getRight();
                            shipHeight = initialHeight * ship.getSize();
                            y1 += initialHeight * ship.getCoords().getLeft() - shipHeight + initialHeight;
                            g.setColor(new Color(255, 255, 0));
                            break;
                        case RIGHT:
                            x1 += initialWidth * ship.getCoords().getRight();
                            y1 += initialHeight * ship.getCoords().getLeft();
                            shipWidth = initialWidth * ship.getSize();
                            g.setColor(new Color(0, 255, 0));
                            break;
                        case LEFT:
                            shipWidth = initialWidth * ship.getSize();
                            x1 += initialWidth * ship.getCoords().getRight() - shipWidth + initialWidth;
                            y1 += initialHeight * ship.getCoords().getLeft();
                            g.setColor(new Color(0, 0, 255));
                            break;
                    }
                    g.fillRect(x1, y1, shipWidth, shipHeight);
                }
            }
            System.out.println(window);
        }
    }
}
