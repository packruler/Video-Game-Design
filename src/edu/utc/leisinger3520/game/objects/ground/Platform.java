package edu.utc.leisinger3520.game.objects.ground;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/5/2015.
 */
public class Platform extends Entity {
    private float r, g, b;

    public Platform(int h, int w, int x, int y, float r, float g, float b) {
        height = h;
        width = w;
        this.x = x;
        this.y = y;

        this.r = r;
        this.g = g;
        this.b = b;
        init();
    }

    public Platform(int h, int w, int x, int y) {
        this(h, w, x, y, .7f, .7f, .7f);
    }

    @Override
    public synchronized void draw() {
        GL11.glColor3f(r, g, b);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(x, y);
        GL11.glVertex2i(x + width, y);
        GL11.glVertex2i(x + width, y + height);
        GL11.glVertex2i(x, y + height);

        GL11.glEnd();
    }
}
