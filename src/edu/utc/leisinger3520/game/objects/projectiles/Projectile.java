package edu.utc.leisinger3520.game.objects.projectiles;

import edu.utc.leisinger3520.game.audio.AudioManager;
import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.physics.Gravity;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ethan Leisinger on 9/9/2015.
 */
public class Projectile extends Entity {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 50;
    public static final float VELOCITY = 1f;
    private static final Vector2f MAX_VELOCITY = new Vector2f(1f, 1f);

    public Projectile(int x, int y, float xV, float yV) {
        super(x, y, HEIGHT, WIDTH);
        velocity.set(xV, yV);
        mass = 2;

    }

    @Override
    public void update(float delta) {
        Gravity.getInstance().updateVelocity(velocity, mass, delta);

        setX((int) (velocity.getX() * delta));
        setY((int) (velocity.getY() * delta));

        if (getX() > Display.getWidth() || getY() < 0 ||
                getY() > Display.getHeight() || getY() < -getHeight())
            active = false;
    }

    @Override
    public synchronized void draw() {
        GL11.glColor3f(1f, 0, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2d(getX(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY());
        GL11.glVertex2d(getX() + getWidth(), getY() + getHeight());
        GL11.glVertex2d(getX(), getY() + getWidth());

        GL11.glEnd();
    }

    @Override
    public void onCollision(Entity other) {
        AudioManager.getInstance().play("boom");
    }
}
