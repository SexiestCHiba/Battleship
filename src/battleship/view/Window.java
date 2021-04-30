package battleship.view;

import battleship.control.WindowKeyboardListener;
import battleship.control.WindowMouseListener;
import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

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
        return keyboardInput();
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
                    ship.setCoords(mouseInput(player));
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

    @Override
    protected String keyboardInput() throws InterruptedException {
        keyboardComponent.requestInput = true;
        while(true) {
            Thread.sleep(25);
            if(keyboardComponent.keyTyped != KeyEvent.CHAR_UNDEFINED) {
                keyboardComponent.requestInput = false;
                String value = String.valueOf(keyboardComponent.keyTyped).toUpperCase();
                keyboardComponent.keyTyped = KeyEvent.CHAR_UNDEFINED;
               return value;
            }
        }
    }

    @Override
    protected Pair<Integer, Integer> mouseInput(Player player) throws InterruptedException {
        mouseComponent.requestInput = true;
        while(true) {
            Thread.sleep(25);
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

    public void openDialog(String message, boolean exitOnClose) {
        JOptionPane.showMessageDialog(frame, message);
        if(exitOnClose)
            System.exit(0);
    }

    public void openDialog(String message) {
        openDialog(message, false);
    }

    @Override
	public void displayBoard() {
        frame.paintComponents(frame.getGraphics());
	}

    @Override
    public Pair<Integer, Integer> chooseMove(Player player) throws InterruptedException {
        setUpperText("Joueur " + player.getId() + " cliquer sur l'emplacement ou vous souhaitez tirer");
        frame.repaint();
        if(player instanceof Human) {
            Pair<Integer, Integer> coords = new Pair<>(-1, -1);
            boolean valid = false;
            while(!player.areValid(coords.getLeft(), coords.getRight())) {
                if(valid)
                    openDialog("Erreur de placement, ce coup a déjà été effectué");
                valid = true;
                coords = mouseInput(game.getOtherPlayer(player));
            }
            return coords;
        }
        return super.chooseMove(player);

    }

    @Override
    public void displayWinner(Player winner) {
        openDialog("Le joueur " + winner.getId() + " a gagné(e)", true);
    }

    class Draw extends JPanel {

        private final Window window;

        public Draw(Window window) {
            this.window = window;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawString(upperTitle, (int) (window.width /2 - (upperTitle.length() * 2.5)), 50);
            g2d.drawString(upperSubTitle, (int) (window.width / 2 -  (upperSubTitle.length() * 2.5)), 65);
            int width = window.width;
            int height = window.height;
            int initialHeight = height / 12;
            int initialWidth = width / 23;
            for(int abscisse = initialWidth; abscisse < width; abscisse += initialWidth) {
                g2d.drawLine(abscisse, initialHeight * 2, abscisse, height);
                if(abscisse == initialWidth * 11)
                    abscisse += initialWidth;
            }
            for(int ordonnee = initialHeight * 2; ordonnee < height + 1; ordonnee += initialHeight) {
                g2d.drawLine(initialWidth, ordonnee, initialWidth * 11, ordonnee);
                g2d.drawLine(initialWidth * 13, ordonnee, width - 4, ordonnee);
            }

            for(int i = 1; i < 3; ++i) {
                Player player = game.players[i-1];
                for(Ship ship : player.getShips()) {
                    int x1 = i == 1 ? initialWidth : initialWidth * 13;
                    int y1 = initialHeight * 2;
                    int shipWidth = initialWidth;
                    int shipHeight = initialHeight;
                    switch(ship.getDirection()) {
                        case DOWN:
                            x1 += initialWidth * ship.getCoords().getRight();
                            y1 += initialHeight * ship.getCoords().getLeft();
                            shipHeight = initialHeight * ship.getSize();
                            g.setColor(new Color(0, 255, 255));
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
                    g2d.fillRoundRect(x1 + 1, y1 + 1, shipWidth - 1, shipHeight - 1, 25, 25);
                }
            }
            for(int i = 1; i < 3; ++i) {
                Player player = game.players[i-1];
                int halfBoxSizeWidth = initialWidth / 2;
                int halfBoxSizeHeight = initialHeight / 2;
                float rectangleSize =  initialWidth / 4f;
                int sqrt = (int) Math.sqrt(initialHeight * initialHeight + initialWidth * initialWidth) - 10;
                for(Triplet<Integer, Integer, Boolean> move : player.getMoves()) {
                    int x1 = (i == 1 ? initialWidth * 13 : initialWidth) + initialWidth * move.getMiddle();
                    int y1 = initialHeight * 2 + initialHeight * move.getLeft() + 8;
                    RoundRectangle2D cross1 = new RoundRectangle2D.Float(x1, y1, rectangleSize, sqrt, 15, 15);
                    RoundRectangle2D cross2 = new RoundRectangle2D.Float(x1 + initialWidth - 9, y1 - 9, rectangleSize, sqrt, 15, 15);
                    if(move.getRight()) {
                        g.setColor(new Color(255, 0, 0));
                    } else {
                        g.setColor(new Color(0, 123, 255));
                    }
                    g2d.rotate(Math.toRadians(-45), x1, y1);
                    g2d.fill(cross1);
                    g2d.rotate(Math.toRadians(45), x1, y1);
                    g2d.rotate(Math.toRadians(45), x1 + initialWidth - 9, y1 - 9);
                    g2d.fill(cross2);
                    g2d.rotate(Math.toRadians(-45), x1 + initialWidth - 9, y1 - 9);


                }
            }
            System.out.println(window);
        }
    }
}
