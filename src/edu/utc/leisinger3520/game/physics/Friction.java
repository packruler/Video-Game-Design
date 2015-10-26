package edu.utc.leisinger3520.game.physics;

import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by packr on 10/9/2015.
 */
public class Friction extends Force {
    private static final Friction instance = new Friction();
    private static final float resRatio = 75;

    public Friction() {
        immediate = false;
    }

    public static Friction getInstance() {
        return instance;
    }

    @Override
    protected void addForce(Vector2f velocity, float delta, float mass) {
        if (velocity.getX() != 0f) {
            float adjustment = (((velocity.getX() / resRatio) * (delta / mass)));
            if (velocity.getX() < 0f) {
                velocity.setX(velocity.getX()- adjustment);
                if (velocity.getX() > -0.01f) {
                    velocity.setX(0);
                }
            } else {
                velocity.setX(velocity.getX() - adjustment);
                if (velocity.getX() < 0.01f)
                    velocity.setX(0);
            }
        }

//        if (velocity.getY() != 0) {
//            if (velocity.getY() < 0) {
//                velocity.setY(velocity.getY() - (((velocity.getY() / resRatio) * (delta / mass))));
//                if (velocity.getY() > 0)
//                    velocity.setY(0);
//            } else {
//                velocity.setY(velocity.getY() - (((velocity.getY() / resRatio) * (delta / mass))));
//                if (velocity.getY() < 0)
//                    velocity.setY(0);
//            }
//        }
    }
}
