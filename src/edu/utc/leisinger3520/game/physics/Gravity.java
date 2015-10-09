package edu.utc.leisinger3520.game.physics;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ethan Leisinger on 9/16/2015.
 */
public class Gravity extends Force {
    public static float GRAVITY = .001f;
    private static Gravity INSTANCE = new Gravity();

    public static Gravity getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Gravity();
        return INSTANCE;
    }

    private Gravity() {
        force.set(0, GRAVITY);
        immediate = false;
    }

    @Override
    public void updateVelocity(Vector2f velocity, float mass, float delta) {
        super.updateVelocity(velocity, mass, delta);
    }
}
