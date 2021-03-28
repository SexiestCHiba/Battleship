package battleship.model;

import battleship.model.player.Player;
import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.View;

import java.util.Scanner;

public class Game {

    public Player[] players;
    public Player currentPlayer;
    private int[] ships = new int[5];

    public Game(Player[] players) {
        this.players = players;
        this.currentPlayer = players[0];
        players[0].setId(1);
        players[1].setId(2);
        ships[0] = 5;
        ships[1] = 4;
        ships[2] = 3;
        ships[3] = 3;
        ships[4] = 2;
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
            if(!ship.hasDrown())
                otherPlayer.isItDrown(ship);
        }
    }
    public Player getWinner(){
        int cpt = 0;
        for(Ship ship : players[0].getShips()){
            if(!ship.hasDrown())
                break;
            else
                cpt ++;
        }
        if(cpt == 5)
            return players[1];
        cpt = 0;
        for(Ship ship : players[1].getShips()){
            if(!ship.hasDrown())
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
        Ship ship = new Ship(new Pair<>(0,0),2,new Pair<>(-1,-1));
        Scanner scanner = new Scanner(System.in);
        int x,y;
        String dir = "AB";
        Pair pair = new Pair<>(null,null);
        for(int i : ships){
            while(!player.setShips(ship)){
                System.out.println("Placement du bateau de longueur "+i +" :\n");
                System.out.println("Veuillez indiquer la coordonée x de votre bateau");
                x = scanner.nextInt();
                System.out.println("Veuillez indiquer la coordonée y de votre bateau");
                y = scanner.nextInt();
                ship.setCoords(new Pair<>(x,y));
                while(!dir.equals("D") || !dir.equals("H") || !dir.equals("B") || !dir.equals("G")){
                    System.out.println("Veuillez indiquer la direction de placement de votre bateau (d droite, h haut, b bas, g gauche)");
                    dir = scanner.nextLine();
                    System.out.println(dir);
                    dir = (String.valueOf(dir.substring(0,1))).toUpperCase();

                }
                switch (dir){
                    case "D":
                        ship.setDirection(new Pair<>(1,0));
                        break;
                    case "H":
                        ship.setDirection(new Pair<>(0,1));
                        break;
                    case "B":
                        ship.setDirection(new Pair<>(0,-1));
                        break;
                    case "G":
                        ship.setDirection(new Pair<>(-1,0));
                        break;
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
