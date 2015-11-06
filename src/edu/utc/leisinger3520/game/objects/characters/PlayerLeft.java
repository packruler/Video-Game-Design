package edu.utc.leisinger3520.game.objects.characters;

import org.lwjgl.opengl.Display;

/**
 * Created by packr on 11/6/2015.
 */
public class PlayerLeft extends Player {
    public PlayerLeft() {
        super(RADIUS, Display.getHeight() / 2);
        fill.setG(1);
    }
}
