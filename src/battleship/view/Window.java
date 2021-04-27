package battleship.view;

import battleship.control.WindowMouseListener;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;

import javax.swing.*;
import java.awt.*;

public class Window extends AbstractView {

    final JFrame frame;

    public final int height = 600;
    public final int width = 1200;
    String upperText = "";

    public Window(Game game) {
        super(game);
        this.frame = new JFrame("Battleship");
        frame.setSize(width + width / 13, height + height / 4);
        frame.setResizable(false);
        frame.setContentPane(new Draw(this));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(new WindowMouseListener(this));

    }

    @Override
    public void setShips(Player player) {
        upperText = "Placez votre navire joueur " + player.getId();
        if(player instanceof Human) {
            for(int i : shipsSize) {
                upperText += "Placez votre premier navire de taille " + i + " à l'aide de la souris";
            }

        } else {
            super.setShips(player);
        }
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
            g.drawString(upperText, (int) (window.width /2 - (upperText.length() * 2.5)), 50);
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
            // TODO: 12/04/2021 Dessiner les navires

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
