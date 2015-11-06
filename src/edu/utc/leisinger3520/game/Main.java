package edu.utc.leisinger3520.game;

import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.scenes.HockeyGame;
import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class Main {
    public static final int TARGET_FPS = 30;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    public static Scene scene;

    public static void main(String[] args) throws LWJGLException {
        initGL(SCR_WIDTH, SCR_HEIGHT);

        long now = System.currentTimeMillis();
        long delta;

        scene = new HockeyGame();



        while (!Display.isCloseRequested()) {

            // UPDATE DISPLAY
            Display.update();
            Display.sync(TARGET_FPS);
            delta = System.currentTimeMillis() - now;
            now = System.currentTimeMillis();



                // DRAW OBJECTS
                scene.drawFrame(delta);

        }
        Entity.closePool();
        Display.destroy();
    }


    public static void initGL(int width, int height) throws LWJGLException {
        // open window of appropriate size
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.create();
        Display.setVSyncEnabled(true);

        // enable 2D textures
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // for text display
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);

        // set "clear" color to black
        GL11.glClearColor(1f, 1f, 1f, 1f);

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // set viewport to entire window
        GL11.glViewport(0, 0, width, height);

        // set up orthographic projectionr
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
//         GLU.gluPerspective(90f, 1.333f, 2f, -2f);
//         GL11.glTranslated(0, 0, -500);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
