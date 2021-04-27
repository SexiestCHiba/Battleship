package battleship.control;

import battleship.utils.Pair;
import battleship.view.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowMouseListener implements MouseListener {

    private final Window window;
    public boolean requestInput = false;
    public Pair<Integer, Integer> lastInput = null;
    public int playerIdLastInput = 0;

    public WindowMouseListener(Window view) {
        this.window = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(requestInput) {
            int x = e.getX() - 7;
            int y = e.getY() - 30;
            int initialHeight = window.height / 12;
            int initialWidth = window.width / 23;
            if(y >= initialHeight * 2 && y <= window.height) {
                y -= initialHeight * 2;
                if(x >= initialWidth && x <= initialWidth * 11) {
                    x -= initialWidth;
                    lastInput = new Pair<>(y / initialHeight, x / initialWidth);
                    playerIdLastInput = 1;
                } else if(x >= initialHeight * 13 && x <= window.width) {
                    x -= initialWidth * 13;
                    lastInput = new Pair<>(y / initialHeight, x / initialWidth);
                    playerIdLastInput = 2;
                }
            }
        }
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
