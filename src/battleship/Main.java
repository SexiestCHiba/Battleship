package battleship;

import battleship.model.Game;
import battleship.model.player.AbstractPlayer;
import battleship.model.player.Human;
import battleship.model.player.Random;
import battleship.utils.Pair;
import battleship.view.AbstractView;
import battleship.view.Terminal;
import battleship.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {

    public static AbstractView view;
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

    private static void parseArgs(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        AbstractPlayer[] players = new AbstractPlayer[2];
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
            game = new Game(players);
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
