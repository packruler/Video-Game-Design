package edu.utc.leisinger3520.game.physics;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ethan Leisinger on 10/7/2015.
 */
public class AirResistance extends Force {
    private static AirResistance instance = new AirResistance();
    private static Vector2f xResistance = new Vector2f(.0001f, 0);
    private static Vector2f yResistance = new Vector2f(0, .0001f);

    public static AirResistance getInstance() {
        return instance;
    }


    private AirResistance() {
        immediate = false;

    }

    @Override
    protected void addForce(Vector2f velocity, float delta) {
        if (velocity.getX() != 0) {
            if (velocity.getX() < 0) {
                velocity.setX(velocity.getX() + (xResistance.getX() * delta));
                if (velocity.getX() > 0)
                    velocity.setY(0);
            } else {
                velocity.setX(velocity.getX() - (xResistance.getX() * delta));
                if (velocity.getX() < 0)
                    velocity.setX(0);
            }
        }

        if (velocity.getY() != 0) {
            if (velocity.getY() < 0) {
                velocity.setY(velocity.getY() + (yResistance.getY() * delta));
                if (velocity.getY() > 0)
                    velocity.setY(0);
            } else {
                velocity.setY(velocity.getY() - (yResistance.getY() * delta));
                if (velocity.getY() < 0)
                    velocity.setY(0);
            }
        }
    }
}
