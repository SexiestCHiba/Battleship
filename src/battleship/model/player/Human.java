package battleship.model.player;

import battleship.utils.Pair;
import battleship.utils.Triplet;
import battleship.view.Terminal;

import java.util.Scanner;

public class Human extends Player {



    @Override
    public Pair<Integer,Integer> chooseMove() {
        int x = -1, y = -1;
        Scanner scanner = Terminal.scanner;
        while(!areValid(x,y)) {
            System.out.println("Veuillez indiquer la coordonée x de votre coup");
            x = scanner.nextInt();
            System.out.println("Veuillez indiquer la coordonée y de votre coup");
            y = scanner.nextInt();
        }
        return new Pair<>(x,y);
    }
    public boolean areValid(int x,int y){
        if(x <0 || x >10 || y <0 || y >10)
            return false;
        for(Triplet<Integer,Integer,Boolean> move : moves){
            if((move.getLeft() == x) && (move.getMiddle() == y) )
                return false;
        }
        return true;
    }
    @Override
    public String toString(){
        return "Human " +id;
    }

}
