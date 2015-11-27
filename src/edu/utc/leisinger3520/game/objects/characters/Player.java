package edu.utc.leisinger3520.game.objects.characters;

import edu.utc.leisinger3520.game.objects.Circle;

/**
 * Created by packr on 11/6/2015.
 */
public class Player extends Circle {
    public static final int RADIUS = 60;
    protected float acceleration = 1 / 20f;
    private float accelMult = 1.5f;

    public Player(int x, int y) {
        super(x, y, RADIUS);
    }

    public void onMoveUp() {
        float y = velocity.getY();
        float acceleration = this.acceleration;
        if (y > 0)
//            acceleration *= accelMult;
            y = 0;
        else if (y > -maxV)
            y -= acceleration;
//        if (y < -maxV)
//            y = -maxV;
        velocity.setY(y);
        mass = 50;
    }

    public void onMoveDown() {
        float y = velocity.getY();
        float acceleration = this.acceleration;
        if (y < 0)
//            acceleration *= accelMult;
            y = 0;
        else if (y < maxV)
            y += acceleration;
//        if (y > maxV)
//            y = maxV;
        velocity.setY(y);
    }

    public void onMoveLeft() {
        float x = velocity.getX();
        float acceleration = this.acceleration;
        if (x > 0)
//            acceleration *= accelMult;
            x = 0;
        if (x > -maxV)
            x -= acceleration;
//        if (x < -maxV)
//            x = -maxV;
        velocity.setX(x);

    }

    public void onMoveRight() {
        float x = velocity.getX();
        float acceleration = this.acceleration;
        if (x < 0)
//            acceleration *= accelMult;
            x = 0;
        else if (x < maxV)
            x += acceleration;
//        if (x > maxV)
//            x = maxV;
        velocity.setX(x);
    }
}
