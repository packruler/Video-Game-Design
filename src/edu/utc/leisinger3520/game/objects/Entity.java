package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.projectiles.Projectile;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Ethan Leisinger on 8/24/2015.
 */
public abstract class Entity {
    protected int x = 0;
    protected int y = 300;
    protected int width = 50;
    protected int height = 50;
    public Rectangle hitbox = new Rectangle();
    protected Vector2f velocity = new Vector2f();
    protected float mass = 0;

    private static ExecutorService backgroundPool = Executors.newFixedThreadPool(4);
    private Future backgroundProcess;

    protected boolean active = true;

    public Entity() {

    }

    public Entity(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        init();
    }

    public void init() {
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
                    updateHitBox();
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

    public void onKeyDown(final int keyDown) {

    }

    public synchronized void updateHitBox() {
        hitbox.x = x;
        hitbox.y = y;
    }

    public synchronized boolean intersects(Entity other) {
        return hitbox.intersects(other.hitbox);
    }

    public synchronized Rectangle intersection(Entity other) {
        return hitbox.intersection(other.hitbox);
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
}
