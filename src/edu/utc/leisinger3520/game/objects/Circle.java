package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.display.Color;
import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.opengl.Display;
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

        double radius = getRadius();
        double x = getCenterX();
        double y = getCenterY();


        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        fill.use();

        GL11.glVertex2d(x, y); // center of circle
        double twicePi = 2 * Math.PI;
        for (int i = 0; i <= NUM_SEGMENTS; i++) {
            GL11.glVertex2d(
                    x + (radius * Math.cos(i * twicePi / NUM_SEGMENTS)),
                    y + (radius * Math.sin(i * twicePi / NUM_SEGMENTS))
            );
        }

        GL11.glEnd();
    }

    @Override
    public void update(float delta) {
        int x = (int) (getX() + velocity.getX() * delta);
        int y = (int) (getY() + velocity.getY() * delta);
//Log.i(getWidth()+" | "+ getX()+ " | " +Display.getX());
        if (x < 0) {
            setX(0);
            velocity.x = velocity.x * -.9f;
        } else if (x + getWidth() > Display.getWidth()) {
            setX((int) (Display.getWidth() - getWidth()));
            velocity.x = velocity.x * -.9f;
        } else setX(x);
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

    @Override
    public synchronized void onCollision(Entity other) {
        if (!(other instanceof Circle))
            super.onCollision(other);

        float newVelocityX = (velocity.x * (mass - other.mass) + (2 * other.mass * other.velocity.x) / (mass + other.mass));
        float newVelocityY = (velocity.y * (mass - other.mass) + (2 * other.mass * other.velocity.y) / (mass + other.mass));

        velocity.set(newVelocityX, newVelocityY);
        Log.i(velocity);


    }
}
