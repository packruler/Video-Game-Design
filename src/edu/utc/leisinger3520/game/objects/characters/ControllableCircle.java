package edu.utc.leisinger3520.game.objects.characters;

import edu.utc.leisinger3520.game.objects.Circle;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Created by packr on 11/6/2015.
 */
public class ControllableCircle extends Circle {

    public ControllableCircle(int x, int y, int radius) {
        super(x, y, radius);
    }

    @Override
    public void update(float delta) {
        if (Mouse.isButtonDown(0)) {
            double mx = Mouse.getX() - (getWidth() / 2);
            double my = Display.getHeight() - Mouse.getY() - (getHeight() / 2);

            velocity.set((float) ((mx - getX()) * .01), (float) ((my - getY()) * .01));

            setX((int) (getX() + velocity.getX() * delta));
            setY((int) (getY() + velocity.getY() * delta));
        }
    }
}
