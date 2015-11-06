package edu.utc.leisinger3520.game.objects.ground;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created by packr on 11/6/2015.
 */
public class Goal extends Entity {
    public static final int HEIGHT = 200;
    public static final int WIDTH = 10;

    public Goal(boolean onLeft) {
        super();
        if (onLeft) {
            hitbox.setRect(0, Display.getHeight() / 2 - HEIGHT / 2, WIDTH, HEIGHT);
            fill.setR(1);
        } else {
            hitbox.setRect(Display.getWidth() - WIDTH, Display.getHeight() / 2 - HEIGHT/2, WIDTH, HEIGHT);
            fill.setG(1);
        }
    }

    @Override
    public synchronized void draw() {
        GL11.glBegin(GL11.GL_QUADS);

        fill.use();
        GL11.glVertex2d(getX(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY() + getHeight());
        GL11.glVertex2d(getX(), getY() + getHeight());

        GL11.glEnd();

    }
}
