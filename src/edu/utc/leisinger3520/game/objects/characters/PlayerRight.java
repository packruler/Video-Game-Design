package edu.utc.leisinger3520.game.objects.characters;

import org.lwjgl.opengl.Display;

/**
 * Created by packr on 11/6/2015.
 */
public class PlayerRight extends Player {
    public PlayerRight() {
        super(Display.getWidth() - RADIUS, Display.getHeight() / 2);
        fill.setR(1);
    }

    @Override
    public void update(float delta) {
        if (getX() < Display.getWidth() / 2) {
            velocity.x = 0;
            setX(Display.getWidth() / 2);
        }

        super.update(delta);
    }
}
