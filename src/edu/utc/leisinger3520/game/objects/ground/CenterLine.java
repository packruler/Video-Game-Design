package edu.utc.leisinger3520.game.objects.ground;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.Display;

/**
 * Created by packr on 11/6/2015.
 */
public class CenterLine extends Entity {
    private static final int WIDTH = 10;

    public CenterLine() {
        hitbox.setRect(Display.getWidth() / 2 - WIDTH / 2, 0, WIDTH, Display.getHeight());
        fill.setB(1);
    }
}
