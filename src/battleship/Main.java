package battleship;

import battleship.model.Game;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.model.player.Random;
import battleship.utils.Pair;
import battleship.view.Terminal;
import battleship.view.View;
import battleship.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {

    public static View view;
    public static Game game;

    public static void main(String[] args) {
        try {
            parseArgs(args);
        } catch (NoSuchElementException e) {
            System.out.println("Pas assez d'arguments, veuillez donner des arguments de cette forme:");
            System.out.println("java -jar battleship <Human/Random> <Human/Random> <Int/Taille de la grille> [nogui]");
            System.out.println("<param>: paramètre obligatoire");
            System.out.println("[param]: paramètre optionnel");
            System.exit(2);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void parseArgs(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Player[] players = new Player[2];
        ArrayList<Pair<String, Class<? extends Player>>> playerClass = new ArrayList<>(2);
        playerClass.add(new Pair<>("human", Human.class));
        playerClass.add(new Pair<>("random", Random.class));
        if(args.length >= 3) {
            for(int i = 0; i < 2; ++i) {
                for (Pair<String, Class<? extends Player>> pair : playerClass) {
                    if(args[i].equalsIgnoreCase(pair.getLeft())) {
                        players[i] = pair.getRight().getDeclaredConstructor().newInstance();
                    }
                }
            }
            game = new Game(players,Integer.parseInt(args[2]));
            if(args.length >= 4) {
                // arguments > 3 ignorés
                if(args[3].equalsIgnoreCase("nogui"))
                    view = new Terminal(game);
                else
                    view = new Window(game);
            }
        } else
            throw new NoSuchElementException("Pas assez d'arguments");
    }
}