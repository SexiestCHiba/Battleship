package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.View;

import java.util.Scanner;

public class Game {

    public Player[] players;
    public Player currentPlayer;
    private final int[] ships = { 5, 4, 3, 3, 2};

    public Game(Player[] players) {
        this.players = players;
        this.currentPlayer = players[0];
        players[0].setId(1);
        players[1].setId(2);
    }


    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void changeCurrentPlayer(){
        currentPlayer = (currentPlayer == players[1])? players[0] : players[1];
    }

    public void checkDrownedShips(){
        Player otherPlayer = (currentPlayer == players[1])? players[0] : players[1];
        for(Ship ship : currentPlayer.getShips()){
            if(!ship.isDrown())
                otherPlayer.isItDrown(ship);
        }
    }

    public Player getWinner(){
        int cpt = 0;
        for(Ship ship : players[0].getShips()){
            if(!ship.isDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[1];
        cpt = 0;
        for(Ship ship : players[1].getShips()){
            if(!ship.isDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[0];
        return null;

    }
    public void move(Pair<Integer,Integer> move){
        boolean bool = false;
        Player player = (currentPlayer == players[1])? players[0] : players[1];
        for (Ship ship : player.getShips()) {
            for(Pair<Integer,Integer> pair : ship.getCoordsArray()){
                if ((pair.getRight().equals(move.getRight())) && (pair.getLeft().equals(move.getLeft()))) {
                    bool = true;
                    break;
                }
            }
        }
        currentPlayer.addMove(new Triplet<>(move.getLeft(),move.getRight(),bool));

    }
    public void setShips(Player player){
        Scanner scanner = new Scanner(System.in);
        int x, y;
        String dir;
        for(int i : ships){
            boolean valid = false;
            Ship ship = new Ship(new Pair<>(0,0), i, Direction.DEFAULT);
            while(!player.setShips(ship)) {
                if(valid) {
                    System.out.println("Erreur");
                }
                valid = true;
                System.out.println("Placement du bateau de longueur "+ ship.getSize());
                System.out.println("Veuillez indiquer la coordonée x de votre bateau");
                x = scanner.nextInt();
                System.out.println("Veuillez indiquer la coordonée y de votre bateau");
                y = scanner.nextInt();
                ship.setCoords(new Pair<>(x, y));
                boolean validDirection = false;
                while(!validDirection){
                    System.out.println("Veuillez indiquer la direction de placement de votre bateau (d droite, h haut, b bas, g gauche)");
                    dir = scanner.next().toUpperCase();
                    System.out.println(dir);
                    for(Direction direction : Direction.values()) {
                        if(direction.getKeyword() != null && direction.getKeyword().equals(dir)) {
                            ship.setDirection(direction);
                            validDirection = true;
                            break;
                        }
                    }
                }
            }
        }


    }
    public Player Play(View view){
        setShips(players[0]);
        setShips(players[1]);
        while(getWinner() == null){
            System.out.println(view);
            Pair<Integer,Integer> move  = currentPlayer.chooseMove();
            move(move);
            changeCurrentPlayer();
            checkDrownedShips();
        }
        return getWinner();
    }


}
