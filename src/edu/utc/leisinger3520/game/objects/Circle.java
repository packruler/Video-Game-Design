package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.display.Color;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class Circle extends Entity {
    private static final int NUM_SEGMENTS = 360;
    protected Color fill;

    public Circle(int x, int y, int radius) {
        hitbox.setRect(x - radius, y - radius, radius * 2, radius * 2);
        fill = new Color(0, 0, 0);
    }

    @Override
    public synchronized void draw() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

        float radius = (float) (getWidth() / 2);
        float x = (float) (getX() + radius);
        float y = (float) (getY() + radius);


        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        fill.use();

        GL11.glVertex2f(x, y); // center of circle
        double twicePi = 2 * Math.PI;
        for (int i = 0; i <= NUM_SEGMENTS; i++) {
            GL11.glVertex2f(
                    (float) (x + (radius * Math.cos(i * twicePi / NUM_SEGMENTS))),
                    (float) (y + (radius * Math.sin(i * twicePi / NUM_SEGMENTS)))
            );
        }

        GL11.glEnd();
    }

    public double getCenterX() {
        return getX() + getRadius();
    }

    public double getCenterY() {
        return getY() + getRadius();
    }

    public int getRadius() {
        return (int) getWidth() / 2;
    }

    @Override
    public synchronized boolean isCollision(Entity other) {
        if (!(other instanceof Circle))
            return super.isCollision(other);

        double xDif = ((Circle) other).getCenterX() - getCenterX();
        double yDif = ((Circle) other).getCenterY() - getCenterY();
        double dist = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
        int sumRadii = ((Circle) other).getRadius() + getRadius();

        return dist <= sumRadii;
    }
}
