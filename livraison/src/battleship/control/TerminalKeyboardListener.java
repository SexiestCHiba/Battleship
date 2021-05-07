package battleship.control;

import java.util.Scanner;

public class TerminalKeyboardListener {

    private final Scanner scanner;

    public TerminalKeyboardListener(Scanner scanner) {
        this.scanner = scanner;
    }

    public String keyboardInput() {
        return scanner.next();
    }
}
