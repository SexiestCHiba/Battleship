package battleship.view;

import battleship.model.Game;
import battleship.model.player.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.*;

public class Window extends View {

    private JFrame frame;
    private final int hauteur = 750;
    private final int largeur = 1250;	

    public Window(Game game) {
        super(game);
        this.frame = new JFrame("Battleship");
        frame.setSize(largeur,hauteur);
        frame.setContentPane(new Draw());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setShips(Player player) {

    }

    @Override
    public void displayBoard(Graphics g) {
    	
    }
    class Draw extends JPanel {
        public void paintComponent(Graphics g) {
        	/*JTextArea area = new JTextArea();
            area.setBounds(20,10,400,20);
            //area.append("A   B   C   D   E   F   G   H   I   J");
            frame.add(area);*/
            //23 - 12
            for (int i=100; i<largeur; i+=largeur/23) {
            		g.drawLine(i, largeur/12, i, largeur-(largeur/12));
            		if (i > (largeur / 23) * 10 && i < (largeur / 23) * 12 - (largeur/40)) i += largeur/23 ;
            }
            for (int j=100; j<hauteur; j+=hauteur/10) {
            	g.drawLine(hauteur/10, j, hauteur - (hauteur/10), j);
            }
        }
   }
    
    public void designBoard(Graphics g) {
    	frame.paintComponents(g);
    }

	@Override
	public void displayBoard() {
		// TODO Auto-generated method stub
		
	}
}
