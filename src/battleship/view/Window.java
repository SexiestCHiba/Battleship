package battleship.view;

import battleship.model.Game;
import battleship.model.player.Player;
import battleship.utils.Pair;

import javax.swing.*;
import java.awt.*;

public class Window extends AbstractView {

    private final JFrame frame;

    private final int height = 600;
    private final int width = 1200;

    public Window(Game game) {
        super(game);
        this.frame = new JFrame("Battleship");
        frame.setSize(width + width / 13, height + height / 4);
        frame.setContentPane(new Draw());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void setShips(Player player) {

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
        public void paintComponent(Graphics g) {
        	/*JTextArea area = new JTextArea();
            area.setBounds(20,10,400,20);
            //area.append("A   B   C   D   E   F   G   H   I   J");
            frame.add(area);*/
            for (int abscisse = width / 24; abscisse< width +1; abscisse+= width / 24) {
                g.drawLine(abscisse, height /6, abscisse, height);
                if ( width * 0.44167 < abscisse && abscisse < (width >> 1)) {
                    abscisse += width / 24 ;
                }
            }
            for (int ordonnee = height / 6; ordonnee< height +1; ordonnee+= height / 12) {
                g.drawLine(width / 24, ordonnee, (int) (width /2.18), ordonnee);
                g.drawLine((int) (width / 1.845), ordonnee, width, ordonnee);
            }
            TextArea a = new TextArea("Aouiuxdytftgykhulijhguhghf");
            setLayout(new GridLayout(width, height));
            a.replaceRange("dqsdqsfdqsd", 0, 1);
        }
    }
}
