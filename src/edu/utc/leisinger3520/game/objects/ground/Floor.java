package edu.utc.leisinger3520.game.objects.ground;

import edu.utc.leisinger3520.game.MainDeprecated;
import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger (aka Packruler) on 8/31/2015.
 */
public class Floor extends Entity {

    public Floor() {
        width = MainDeprecated.SCR_WIDTH;
        x = 0;
        height = 100;
        y = MainDeprecated.SCR_HEIGHT - height;
    }

    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(.5f, 1f, .5f);

        GL11.glVertex2i(x, y);
        GL11.glVertex2i(x + width, y);
        GL11.glVertex2i(x + width, y + height);
        GL11.glVertex2i(x, y + height);

        GL11.glEnd();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void onKeyDown(int keyDown) {
        super.onKeyDown(keyDown);
    }
}
