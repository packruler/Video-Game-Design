package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.MainDeprecated;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 8/24/2015.
 */
public class Box extends Entity {
    protected boolean autoScroll = false;
    protected boolean autoScrollRight = true;

    private boolean spaceDown = false;


    @Override
    public void update(final float delta) {
//        runInBackground(new Runnable() {
//            @Override
//            public void run() {
                int distance = (int) (delta * .25);

                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
                    moveRight(distance);

                if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
                    moveLeft(distance);

                if (Keyboard.isKeyDown(Keyboard.KEY_UP))
                    moveUp(distance);

                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
                    moveDown(distance);

                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    if (!spaceDown)
                        autoScroll = !autoScroll;
                    spaceDown = true;
                } else spaceDown = false;

                if (autoScroll)
                    if (autoScrollRight)
                        autoScrollRight = moveRight(distance);
                    else autoScrollRight = !moveLeft(distance);
//            }
//        });
    }

    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(.5f, 1f, .5f);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + width);
        GL11.glVertex2f(x, y + width);

        GL11.glEnd();
    }

    public boolean moveRight(int distance) {
        if ((x + width) >= MainDeprecated.SCR_WIDTH)
            return false;

        if ((x + width) - distance >= MainDeprecated.SCR_WIDTH)
            x = MainDeprecated.SCR_WIDTH - width;
        else
            x += distance;
        return true;
    }

    public boolean moveLeft(int distance) {
        if (x <= 0)
            return false;
        if (x - distance <= 0)
            x = 0;
        else
            x -= distance;
        return true;
    }

    public boolean moveUp(int distance) {
        if (y <= 0)
            return false;
        if (y - distance <= 0)
            y = 0;
        else
            y -= distance;
        return true;
    }

    public boolean moveDown(int distance) {
        if ((y + height) - distance >= MainDeprecated.SCR_HEIGHT) {
            y = MainDeprecated.SCR_HEIGHT - height;
            return false;
        } else
            y += distance;
        return true;
    }
}
