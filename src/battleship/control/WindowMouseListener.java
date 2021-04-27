package battleship.control;

import battleship.utils.Pair;
import battleship.view.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowMouseListener implements MouseListener {

    private final Window window;

    public WindowMouseListener(Window view) {
        this.window = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
        int initialHeight = window.height / 12;
        int initialWidth = window.width / 23;
        if(y >= initialHeight * 2 && y <= window.height) {
            y -= initialHeight * 2;
            if(x >= initialWidth && x <= initialWidth * 11) {
                x -= initialWidth;
                System.out.println("Player 1");
                Pair<Integer, Integer> location = new Pair<>(y / initialHeight, x / initialWidth);
                System.out.println(location);
            } else if(x >= initialHeight * 13 && x <= window.width) {
                x -= initialWidth * 13;
                System.out.println("Player 2");
                Pair<Integer, Integer> location = new Pair<>(y / initialHeight, x / initialWidth);
                System.out.println("location: " + location);
            }
        }
        System.out.println("(" + x + ", " + y + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent ignored) {}

    @Override
    public void mouseExited(MouseEvent ignored) {}

}
