package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.display.Color;
import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.projectiles.Projectile;
import org.lwjgl.util.vector.Vector2f;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Ethan Leisinger on 8/24/2015.
 */
public abstract class Entity {
    public Hitbox hitbox = new Hitbox();
    protected Vector2f velocity = new Vector2f();
    protected float mass = 1;
    protected edu.utc.leisinger3520.game.display.Color fill = new Color(0, 0, 0);

    private static ExecutorService backgroundPool = Executors.newFixedThreadPool(4);
    private Future backgroundProcess;

    protected boolean active = true;

    public Entity() {

    }

    public Entity(int x, int y, int height, int width) {
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    public void destroy() {

    }

    public void update(final float delta) {
    }

    protected void runInBackground(final Runnable runnable) {
        backgroundProcess = backgroundPool.submit(new Runnable() {
            @Override
            public void run() {
                /**
                 * Synchronize thread with this Entity pausing the draw() process to pause until the update on each
                 * object is done updating. This "SHOULD" remove requirement for update completion checking loop.
                 */
                synchronized (Entity.this) {
                    runnable.run();
                }
            }
        });
    }

    public boolean isDone() {
        if (backgroundProcess == null)
            return true;

        if (backgroundProcess.isDone()) {
            backgroundProcess = null;
            return true;
        }

        return false;
    }

    public synchronized void draw() {

    }

    public static void closePool() {
        backgroundPool.shutdownNow();
        Log.i("Pool closed");
    }


    public synchronized boolean isCollision(Entity other) {
        return hitbox.intersects(other.hitbox);
    }


    public synchronized void onCollision(Entity other) {
        if (other instanceof Projectile)
            other.setActive(false);
    }

    public synchronized boolean isActive() {
        return active;
    }

    public synchronized void setActive(boolean setTo) {
        active = setTo;
    }

    public float getMass() {
        return mass;
    }

    public double getX() {
        return hitbox.getX();
    }

    public void setX(int x) {
        hitbox.setX(x);
    }

    public double getY() {
        return hitbox.getY();
    }

    public void setY(int y) {
        hitbox.setY(y);
    }

    public double getWidth() {
        return hitbox.getWidth();
    }

    public double getHeight() {
        return hitbox.getHeight();
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
}
