package battleship.control;

import battleship.view.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowKeyboardListener implements KeyListener {

    private final Window window;
    public boolean requestInput = false;
    public char keyTyped = KeyEvent.CHAR_UNDEFINED;

    public WindowKeyboardListener(Window window) {
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(requestInput) {
            if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED)
                keyTyped = e.getKeyChar();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
