package edu.utc.leisinger3520.game.physics;

import com.sun.deploy.config.VerboseDefaultConfig;
import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ethan Leisinger on 9/16/2015.
 */
public abstract class Force {
    protected Vector2f force = new Vector2f(0, 0);
    protected boolean immediate = true;
    protected final static Vector2f MAX_VALUE = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);

    public void updateVelocity(Vector2f velocity, float mass, float delta) {
        updateVelocity(velocity, mass, delta, MAX_VALUE);
    }

    public void updateVelocity(Vector2f velocity, float mass, float delta, Vector2f maxVelocity) {
        if (immediate)
            addForce(velocity);
        else
            addForce(velocity, delta);

        checkVelocity(velocity, maxVelocity);
    }

    protected void checkVelocity(Vector2f velocity, Vector2f maxVelocity) {
        if (maxVelocity.equals(MAX_VALUE))
            return;
        if (maxVelocity.getX() != Float.MAX_VALUE) {
            if (velocity.getX() > maxVelocity.getX())
                velocity.setX(maxVelocity.getX());
            else if (velocity.getX() < -maxVelocity.getX())
                velocity.setX(-maxVelocity.getX());
        }
        if (velocity.getY() > maxVelocity.getY())
            velocity.setY(maxVelocity.getY());
        else if (velocity.getY() < -maxVelocity.getY())
            velocity.setY(-maxVelocity.getY());
    }

    protected void addForce(Vector2f velocity) {
        velocity.setY(velocity.getY() + force.getY());
        velocity.setX(velocity.getX() + force.getX());
    }

    protected void addForce(Vector2f velocity, float delta) {
        velocity.setY(velocity.getY() + (force.getY() * delta));
        velocity.setX(velocity.getX() + (force.getX() * delta));
    }
}
