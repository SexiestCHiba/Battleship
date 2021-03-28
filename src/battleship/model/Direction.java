package battleship.model;

import battleship.utils.Pair;

public enum Direction {

    RIGHT(new Pair<>(0, 1), "D"),
    LEFT(new Pair<>(0,-1), "G"),
    UP(new Pair<>(-1,0), "H"),
    DOWN(new Pair<>(1,0), "B"),
    DEFAULT(new Pair<>(-1,-1), null);

    private final Pair<Integer, Integer> direction;
    private final String keyword;

    <K, U> Direction(Pair<Integer, Integer> ukPair, String keyword) {
        this.direction = ukPair;
        this.keyword = keyword;
    }

    public Pair<Integer, Integer> getDirection() {
        return direction;
    }

    public String getKeyword() {
        return keyword;
    }
}
