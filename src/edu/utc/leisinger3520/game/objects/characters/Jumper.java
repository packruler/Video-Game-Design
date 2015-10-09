package edu.utc.leisinger3520.game.objects.characters;

import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.objects.ground.Platform;
import edu.utc.leisinger3520.game.physics.AirResistance;
import edu.utc.leisinger3520.game.physics.Gravity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger (aka Packruler) on 8/31/2015.
 */
public class Jumper extends Entity {

    private LinkedList<Integer> keysDown = new LinkedList<>();
    private final int jumpPower = 20;
    private float jumpSpeed = 0f;
    private boolean canJump = false;

    public Jumper() {

        hitbox.height = 200;
        hitbox.width = 100;
        hitbox.y = 100;
        hitbox.x = 100;
        mass = 2f;

    }

    @Override
    public void update(float delta) {
        float x = (float) hitbox.getX();
        float y = (float) hitbox.getY();

        canJump = false;
        // fix on boundaries...
        if (x < 0) {
            x = 0;
            velocity.setX(0);
        }

        if (x > Display.getWidth() - hitbox.getWidth()) {
            x = (float) (Display.getWidth() - hitbox.getWidth());
            velocity.setX(0);
        }

        if (y < 0) {
            y = 0;
            velocity.setY(0);
        }

        if (y > Display.getHeight() - hitbox.getHeight()) {
            y = (float) (Display.getHeight() - hitbox.getHeight());
            velocity.setY(0);
        }


        Gravity.getInstance().updateVelocity(velocity, mass, delta);
        AirResistance.getInstance().updateVelocity(velocity, mass, delta);

        Vector2f extraForce = new Vector2f(0, 0);

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            Vector2f.add(extraForce, new Vector2f(0f, -.01f), extraForce); // force going up
        }

        // add some horizontal forces in response to key presses
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            Vector2f.add(extraForce, new Vector2f(-.001f, 0), extraForce);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            Vector2f.add(extraForce, new Vector2f(.001f, 0), extraForce);
        }


        // apply force to velocity vector
        extraForce.scale(delta / mass);
        Vector2f.add(velocity, extraForce, velocity);
//        Log.i(velocity);

        x += velocity.getX() * delta;
        y += velocity.getY() * delta;


        hitbox.setLocation((int) x, (int) y);
    }

    @Override
    public void draw() {
        GL11.glColor3f(1f, 0f, 0f);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2d(hitbox.getX(), hitbox.getY());
        GL11.glVertex2d(hitbox.getX() + hitbox.getWidth(), hitbox.getY());
        GL11.glVertex2d(hitbox.getX() + hitbox.getWidth(), hitbox.getY() + hitbox.getHeight());
        GL11.glVertex2d(hitbox.getX(), hitbox.getY() + hitbox.getHeight());

        GL11.glEnd();

    }

    @Override
    public synchronized void onCollision(Entity other) {
//        if (other instanceof Platform) {
            if (velocity.getY() > 0) {
                Log.i("Going down");
                Rectangle intersection = intersection(other);
                if (intersection.getWidth() > intersection.getHeight()) {
                    velocity.setY(0);
                    hitbox.y = (int) (other.hitbox.getY() - hitbox.height);
                }
            }
//        }
    }
}
