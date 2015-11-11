package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.display.Color;
import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class Circle extends Entity {
    private static final int NUM_SEGMENTS = 360;
    protected Color fill;
    protected float maxV = .5f;
    protected float bounceScalar = .8f;

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
        if (velocity.getX() > maxV)
            velocity.setX(maxV);
        else if (velocity.getX() < -maxV)
            velocity.setX(-maxV);

        if (velocity.getY() > maxV)
            velocity.setY(maxV);
        else if (velocity.getY() < -maxV)
            velocity.setY(-maxV);

        int x = (int) (getX() + velocity.getX() * delta);
        int y = (int) (getY() + velocity.getY() * delta);
//Log.i(getWidth()+" | "+ getX()+ " | " +Display.getX());
        if (x < 0) {
            setX(0);
            velocity.x = velocity.x * -bounceScalar;
        } else if (x + getWidth() > Display.getWidth()) {
            setX((int) (Display.getWidth() - getWidth()));
            velocity.x = velocity.x * -bounceScalar;
        } else setX(x);

        if (y < 0) {
            setY(0);
            velocity.y = velocity.y * -bounceScalar;
        } else if (y + getHeight() > Display.getHeight()) {
            setY((int) (Display.getHeight() - getHeight()));
            velocity.y = velocity.y * -bounceScalar;
        } else setY(y);
    }

    @Override
    public synchronized boolean isCollision(Entity other) {
        if (!(other instanceof Circle))
            return super.isCollision(other);

        double xDif = other.getCenterX() - getCenterX();
        double yDif = other.getCenterY() - getCenterY();
        double dist = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
        int sumRadii = other.getRadius() + getRadius();

        return dist <= sumRadii;
    }

    @Override
    public synchronized void onCollision(Entity other) {
        if (!(other instanceof Circle))
            super.onCollision(other);
//        Vector2f thisPos = new Vector2f((float) getCenterX(), (float) getCenterY());
//        Vector2f otherPos = new Vector2f((float) other.getCenterX(), (float) other.getY());

        double xDif = getCenterX() - other.getCenterX();
        double yDif = getCenterY() - other.getCenterY();
        Vector2f delta = new Vector2f((float) xDif, (float) yDif);
        float d = delta.length();
        float multiplier = ((getRadius() + other.getRadius()) - d) / d;

        // minimum translation distance to push balls apart after intersecting
        Vector2f mtd = new Vector2f(delta.x * multiplier,
                delta.y * multiplier);


        // resolve intersection --
        // inverse mass quantities
        float im1 = 1 / getMass();
        float im2 = 1 / other.getMass();

        setX((int) (getX() + mtd.getX() * (im1 / (im1 + im2))));
        setY((int) (getY() + mtd.getY() * (im1 / (im1 + im2))));

        other.setX((int) (other.getX() + mtd.getX() * (im2 / (im1 + im2))));
        other.setY((int) (other.getY() + mtd.getY() * (im2 / (im1 + im2))));

        Vector2f v = Vector2f.sub(velocity, other.velocity, null);
        float vn = Vector2f.dot(v, mtd.normalise(null));
        if (vn > 0f) {
            Log.e("Escape");
            return;
        }

        Log.i(velocity + " | " + getX() + "," + getY() + " | " + v + " | " + delta + " | " + mtd);
        float i = (-(vn) / im1 + im2);
        mtd = mtd.normalise(null);
        Vector2f impulse = new Vector2f(mtd.getX() * i, mtd.getY() * i);
        Vector2f.add(velocity, (Vector2f) impulse.scale(im1), velocity);
        Vector2f.sub(other.velocity, (Vector2f) impulse.scale(im2), other.velocity);

        if (velocity.getX() == Float.NaN || velocity.getY() == Float.NaN)
            velocity.set(0, 0);
        if (other.velocity.getX() == Float.NaN || other.velocity.getY() == Float.NaN)
            other.velocity.set(0, 0);
    }
}
