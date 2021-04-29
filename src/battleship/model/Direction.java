package battleship.model;

import battleship.utils.Pair;

import java.awt.event.KeyEvent;

public enum Direction {

    RIGHT(new Pair<>(0, 1), "D", KeyEvent.VK_RIGHT),
    LEFT(new Pair<>(0,-1), "G", KeyEvent.VK_LEFT),
    UP(new Pair<>(-1,0), "H", KeyEvent.VK_UP),
    DOWN(new Pair<>(1,0), "B", KeyEvent.VK_DOWN),
    DEFAULT(new Pair<>(-1,-1), null, KeyEvent.VK_UNDEFINED);

    private final Pair<Integer, Integer> direction;
    private final String keyword;
    private final int arrow;

    <K, U> Direction(Pair<Integer, Integer> ukPair, String keyword, int arrow) {
        this.direction = ukPair;
        this.keyword = keyword;
        this.arrow = arrow;
    }

    public Pair<Integer, Integer> getDirection() {
        return direction;
    }

    public String getKeyword() {
        return keyword;
    }
    
    public int getArrow() {
    	return arrow;
    }
}
