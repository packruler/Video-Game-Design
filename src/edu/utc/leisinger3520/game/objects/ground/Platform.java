package edu.utc.leisinger3520.game.objects.ground;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/5/2015.
 */
public class Platform extends Entity {
    private float r, g, b;

    public Platform(int h, int w, int x, int y, float r, float g, float b) {
        super(x, y, h, w);

        fill.set(r, g, b);
    }

    public Platform(int h, int w, int x, int y) {
        this(h, w, x, y, .7f, .7f, .7f);
    }

    @Override
    public synchronized void draw() {
        GL11.glColor3f(r, g, b);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(getX(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY() + getHeight());
        GL11.glVertex2d(getX(), getY() + getHeight());

        GL11.glEnd();
    }
}
