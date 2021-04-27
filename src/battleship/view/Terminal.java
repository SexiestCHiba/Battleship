package battleship.view;

import battleship.model.Direction;
import battleship.model.Game;
import battleship.model.Ship;
import battleship.model.player.Human;
import battleship.model.player.Player;
import battleship.utils.Pair;

import java.util.Scanner;

public class Terminal extends AbstractView {

    public static Scanner scanner = new Scanner(System.in);

    public Terminal(Game game) {
        super(game);
    }

    @Override
    public void setShips(Player player) throws InterruptedException {
        System.out.println("Joueur " + player.getId() + ", placez vos navires");
        int x, y;
        String dir;
        if(player instanceof Human) {
            for(int i : shipsSize) {
                boolean valid = false;
                Ship ship = new Ship(new Pair<>(-1, -1), i, Direction.DEFAULT);
                while (!player.setShips(ship)) {
                    if (valid) {
                        System.out.println("Erreur");
                    }
                    valid = true;
                    System.out.println("Placement du bateau de longueur " + ship.getSize());
                    System.out.println("Veuillez indiquer la coordonée x de votre bateau");
                    x = scanner.nextInt();
                    System.out.println("Veuillez indiquer la coordonée y de votre bateau");
                    y = scanner.nextInt();
                    ship.setCoords(new Pair<>(x, y));
                    boolean validDirection = false;
                    while (!validDirection) {
                        System.out.println("Veuillez indiquer la direction de placement de votre bateau (d droite, h haut, b bas, g gauche)");
                        dir = scanner.next().toUpperCase();
                        for (Direction direction : Direction.values()) {
                            if (direction.getKeyword() != null && direction.getKeyword().equals(dir)) {
                                ship.setDirection(direction);
                                System.out.println(direction);
                                validDirection = true;
                                break;
                            }
                        }
                    }
                    ship.recalculateFullCoords();
                }
            }
        } else {
            super.setShips(player);
            // Computer
        }
    }

    @Override
    public void displayBoard() {
        System.out.println(this);
    }

    @Override
    public Pair<Integer, Integer> chooseMove(Player player) {
        if(player instanceof Human) {
            int x = -1, y = -1;
            while(!player.areValid(x, y)) {
                System.out.println("Veuillez indiquer la coordonée x de votre coup");
                x = scanner.nextInt();
                System.out.println("Veuillez indiquer la coordonée y de votre coup");
                y = scanner.nextInt();
            }
            return new Pair<>(x,y);
        }
        return super.chooseMove(player);

    }

    @Override
    public void displayWinner(Player winner) {
        displayBoard();
        System.out.println("Le joueur " + winner.getId() + " a gagné");
    }

}
