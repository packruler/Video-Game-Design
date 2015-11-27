package edu.utc.leisinger3520.game.objects.characters;

import edu.utc.leisinger3520.game.objects.Circle;
import edu.utc.leisinger3520.game.display.Goal;
import org.lwjgl.opengl.Display;

/**
 * Created by packr on 11/6/2015.
 */
public class Puck extends Circle {
    public static final int RADIUS = 30;

    public Puck() {
        super(Display.getWidth() / 2, Display.getHeight() / 2, RADIUS);
        mass = 10f;
    }

    @Override
    public void update(float delta) {
        if (velocity.getX() > maxV)
            velocity.setX(maxV);
        else if (velocity.getX() < -maxV)
            velocity.setX(-maxV);

        if (velocity.getY() > maxV)
            velocity.setY(maxV);
        else if (velocity.getY() < -maxV)
            velocity.setY(-maxV);

        int x = (int) (getX() + velocity.getX() * delta);
        int y = (int) (getY() + velocity.getY() * delta);
//Log.i(getWidth()+" | "+ getX()+ " | " +Display.getX());
        if (!inGoalY()) {
            if (x < 0) {
                setX(0);
                velocity.x = velocity.x * -bounceScalar;
            } else if (x + getWidth() > Display.getWidth()) {
                setX((int) (Display.getWidth() - getWidth()));
                velocity.x = velocity.x * -bounceScalar;
            } else setX(x);
        } else setX(x);
        if (y < 0) {
            setY(0);
            velocity.y = velocity.y * -bounceScalar;
        } else if (y + getHeight() > Display.getHeight()) {
            setY((int) (Display.getHeight() - getHeight()));
            velocity.y = velocity.y * -bounceScalar;
        } else setY(y);
    }

    public int isGoal() {
        if (inGoalY())
            if (getX() <= -getWidth())
                return -1;
            else if (getX() >= Display.getWidth())
                return 1;
        return 0;
    }

    private boolean inGoalY() {
        return getY() + getWidth() < ((Display.getHeight() / 2) + (Goal.HEIGHT / 2)) && getY() > (Display.getHeight() / 2) - (Goal.HEIGHT / 2);
    }
}
