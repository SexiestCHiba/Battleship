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
            for (int abscisse = width / 24; abscisse< width +1; abscisse+= width / 24) {
                g.drawLine(abscisse, height /6, abscisse, height);
                if ( width * 0.44167 < abscisse && abscisse < width / 2) {
                    abscisse += width / 24 ;
                }
            }
            for (int ordonnee = height / 6; ordonnee< height +1; ordonnee+= height / 12) {
                g.drawLine(width / 24, ordonnee, (int) (width /2.18), ordonnee);
                g.drawLine((int) (width / 1.845), ordonnee, width, ordonnee);
            }
            // TODO: 12/04/2021 Dessiner les navires
            int initialHeight = height / 12;
            for(int i = 1; i < 3; ++i) {
                int initialWidth = width / 24;
                Player player = game.players[i-1];
                System.out.println(i);
                for(Ship ship : player.getShips()) {
                    int x1 = 0;
                    int y1 = 0;
                    int shipWidth = 0;
                    int shipHeight = 0;
                    switch(ship.getDirection()) {
                        case DOWN:
                            x1 = initialWidth * ship.getCoords().getRight();
                            y1 = initialHeight * ship.getCoords().getLeft();
                            shipWidth = initialWidth;
                            shipHeight = initialHeight * ship.getSize();
                            break;
                        case UP:
                            shipWidth = initialWidth;
                            shipHeight = initialHeight * ship.getSize();
                            x1 = initialWidth * ship.getCoords().getRight();
                            y1 = initialHeight * ship.getCoords().getLeft() - shipHeight;
                            break;
                        case RIGHT:
                            x1 = initialWidth * ship.getCoords().getRight();
                            y1 = initialHeight * ship.getCoords().getLeft();
                            shipWidth = initialWidth * ship.getSize();
                            shipHeight = initialHeight;
                            break;
                        case LEFT:
                            shipWidth = initialWidth * ship.getSize();
                            shipHeight = initialHeight;
                            x1 = initialWidth * ship.getCoords().getRight() - shipWidth;
                            y1 = initialHeight * ship.getCoords().getLeft();
                            break;
                    }
                    x1 += i == 1 ? initialWidth : initialWidth + width / 2;
                    y1 += height / 6;
                    g.setColor(new Color(255, 0, 0));
                    g.fillRect(x1, y1, shipWidth, shipHeight);
                }
            }
            System.out.println(window.toString());
        }
    }
}
