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
        this.x = x;
        this.y = y;
        width = WIDTH;
        height = HEIGHT;
        velocity.set(xV, yV);
        mass = 2;

        init();
    }

    @Override
    public void update(float delta) {
        Gravity.getInstance().updateVelocity(velocity, mass, delta);

        x += velocity.getX() * delta;
        y += velocity.getY() * delta;

        if (x > Display.getWidth() || x < 0 ||
                y > Display.getHeight() || y < -width)
            active = false;
    }

    @Override
    public synchronized void draw() {
        GL11.glColor3f(1f, 0, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + width);
        GL11.glVertex2f(x, y + width);

        GL11.glEnd();
    }

    @Override
    public void onCollision(Entity other) {
        AudioManager.getInstance().play("boom");
    }
}
