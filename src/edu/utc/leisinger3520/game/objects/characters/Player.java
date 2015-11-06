package edu.utc.leisinger3520.game.objects.characters;

import edu.utc.leisinger3520.game.objects.Circle;

/**
 * Created by packr on 11/6/2015.
 */
public class Player extends Circle {
    public static final int RADIUS = 60;

    public Player(int x, int y) {
        super(x, y, RADIUS);
    }
}
