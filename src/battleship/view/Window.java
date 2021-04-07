package battleship.view;

import battleship.model.Game;
import battleship.model.player.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.*;

public class Window extends View {

    private JFrame frame;
    private final int hauteur = 600;
    private final int largeur = 1200;	

    public Window(Game game) {
        super(game);
        this.frame = new JFrame("Battleship");
        frame.setSize(largeur+largeur/38,hauteur+hauteur/13);
        frame.setContentPane(new Draw());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setShips(Player player) {

    }

    class Draw extends JPanel {
        public void paintComponent(Graphics g) {
        	/*JTextArea area = new JTextArea();
            area.setBounds(20,10,400,20);
            //area.append("A   B   C   D   E   F   G   H   I   J");
            frame.add(area);*/
            for (int abscisse=largeur/24; abscisse<largeur+1; abscisse+=largeur/24) {
            	g.drawLine(abscisse, hauteur/6, abscisse, hauteur);
            	if ( largeur*0.44167 < abscisse && abscisse < largeur/2) {
            		abscisse += largeur/24 ;
            	}
            }
            for (int ordonnee=hauteur/6; ordonnee<hauteur+1; ordonnee+=hauteur/12) {
            	g.drawLine(largeur/24, ordonnee, (int) (largeur/2.18), ordonnee);
            	g.drawLine((int) (largeur/1.845), ordonnee, largeur, ordonnee);
            }
            TextArea a = new TextArea("Aouiuxdytftgykhulijhguhghf");
            setLayout(new GridLayout(largeur, hauteur));
            a.replaceRange("dqsdqsfdqsd", 0, 1);
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
