package battleship.control;

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
        int x = e.getX();
        int y = e.getY();
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
