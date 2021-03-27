package battleship.view;

import battleship.model.Game;

public abstract class View {


    protected final Game game;

    public View(Game game) {
        this.game = game;
    }
    public abstract String toString();
}
