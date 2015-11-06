package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.objects.ground.Floor;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by Ethan Leisinger on 11/4/2015.
 */
public class Triangle extends Floor {
    public Triangle() {
        hitbox = new Rectangle(600, 500, 200, 200);
    }

    @Override
    public synchronized void draw() {
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        GL11.glColor3f(0, 1, 1);

        GL11.glVertex2d(getX(), getY() + getHeight());
        GL11.glVertex2d(getX() + getWidth(), getY() + getHeight());
        GL11.glVertex2d(getX() + getWidth(), getY());

        GL11.glEnd();
    }

    @Override
    public synchronized boolean intersects(Entity other) {
        if ((other.getX() + other.getHeight() > getX() && other.getX() < getX() + getHeight()) &&
                other.getY() + other.getHeight() > getY() && other.getY() < getY() + getHeight())
            return super.intersects(other);
    }
}
