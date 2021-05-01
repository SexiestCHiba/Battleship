package battleship;

import battleship.model.Game;
import battleship.model.player.AbstractPlayer;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.model.player.Random;
import battleship.utils.Pair;
import battleship.view.Terminal;
import battleship.view.View;
import battleship.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Main class
 * Given arguments are importants, please give attention to {@link Main#parseArgs(String[])} about this
 */
public class Main {

    public static View view;
    public static Game game;

    public static void main(String[] args) {
        try {
            parseArgs(args);
        } catch (NoSuchElementException e) {
            System.out.println("Pas assez d'arguments, veuillez donner des arguments de cette forme:");
            System.out.println("java -jar battleship <Human/Random> <Human/Random> [nogui]");
            System.out.println("<param>: paramètre obligatoire");
            System.out.println("[param]: paramètre optionnel");
            System.exit(2);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        game.Play(view);
    }

    /**
     * <p>Read and parse launch arguments.<br>
     * launch arguments need to follow this syntax:<br></p>
     * <p><strong>{@code <player1_instance> <player2_instance> [nogui]}</strong></p>
     * <p><strong>&#60;arg&#62;</strong> -> mandatory parameter<br>
     * <strong>[arg]</strong> -> optional parameter<br>
     * player_instance values are: <strong>"Human"</strong> or <strong>"Random"</strong><br>
     * nogui to launch the game in terminal or nothing to launch in graphical interface</p>
     * @param args launch arguments
     * @throws NoSuchMethodException reflect exception
     * @throws IllegalAccessException reflect exception
     * @throws InvocationTargetException reflect exception
     * @throws InstantiationException reflect exception
     */
    private static void parseArgs(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Player[] players = new Player[2];
        ArrayList<Pair<String, Class<? extends AbstractPlayer>>> playerClass = new ArrayList<>(2);
        playerClass.add(new Pair<>("human", Human.class));
        playerClass.add(new Pair<>("random", Random.class));
        if(args.length >= 2) {
            for(int i = 0; i < 2; ++i) {
                for (Pair<String, Class<? extends AbstractPlayer>> pair : playerClass) {
                    if(args[i].equalsIgnoreCase(pair.getLeft())) {
                        players[i] = pair.getRight().getDeclaredConstructor().newInstance();
                    }
                }
            }
            if(players[0] != null && players[1] != null) {
                game = new Game(players);
            } else
                throw new IllegalArgumentException("Arguments incorrects: " + Arrays.toString(args));
            if(args.length >= 3) {
                // arguments > 3 ignorés
                if(args[2].equalsIgnoreCase("nogui"))
                    view = new Terminal(game);
                else
                    view = new Window(game);
            } else {
                view = new Window(game);
            }
        } else
            throw new NoSuchElementException("Pas assez d'arguments");
    }
}
