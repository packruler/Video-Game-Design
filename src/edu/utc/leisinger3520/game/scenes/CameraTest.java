package edu.utc.leisinger3520.game.scenes;

import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/5/2015.
 */
public class CameraTest extends Scene {
    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glMatrixMode(GL11.GL_PROJECTION);

        return true;
    }
}
