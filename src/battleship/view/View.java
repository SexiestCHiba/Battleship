package battleship.view;

import battleship.model.Game;

public abstract class View {


    private final Game game;

    public View(Game game) {
        this.game = game;
    }
}
