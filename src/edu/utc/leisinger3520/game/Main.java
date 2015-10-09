// skeleton game driver
// ctanis 8/19/15

package edu.utc.leisinger3520.game;

import edu.utc.leisinger3520.game.audio.AudioManager;
import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.scenes.PlatformTest;
import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.LinkedList;


public class Main {
    public static final int TARGET_FPS = 60;
    public static final int SCR_WIDTH = 1920;
    public static final int SCR_HEIGHT = 1080;

    public static int x = 0;
    public static int y = 300;
    public static final int width = 50;


    public static LinkedList<Entity> ENTITIES = new LinkedList<>();
    public static Scene scene;

    public static void main(String[] args) throws LWJGLException {
        initGL(SCR_WIDTH, SCR_HEIGHT);

        long now = System.currentTimeMillis();
        long delta;
        long avg;

//        scene=MainGame.getInstance();
//        MainGame.getInstance().addEntity(new MouseFollower(200, "./res/Pacman.png"));
//        MainGame.getInstance().addEntity(new AirplaneScrolling(200, "./res/airplane.png"));

        scene = new PlatformTest();

        try {
            AudioManager.getInstance().loadSample("boom", "./res/boom.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }

        long nsNow = System.nanoTime();
        long nsTotal = 0;
        long frames = 0;
        long elapsed;
        while (!Display.isCloseRequested()) {

            // UPDATE DISPLAY
            Display.update();
            Display.sync(TARGET_FPS);
            delta = System.currentTimeMillis() - now;
            now = System.currentTimeMillis();

//            elapsed = (System.nanoTime() - nsNow);
//            nsTotal += elapsed;
//            nsNow = System.nanoTime();
//            frames++;
//            avg = nsTotal / frames;
//
//            Log.i("Frame: " + frames + " | Frame avg: " + avg + " | Last frame: " + elapsed +
//                    " | Projectile count: " + MainGame.PROJECTILES.size());


            // DRAW OBJECTS
            scene.drawFrame(delta);

//            GL11.glEnd();
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
        // GLU.gluPerspective(90f, 1.333f, 2f, -2f);
        // GL11.glTranslated(0, 0, -500);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private static void multiThreadedUpdatingChecker() {
        boolean done = false;
        Object syncObject = new Object();

        synchronized (syncObject) {
            while (!done) {
                done = true;
                for (Entity entity : ENTITIES) {
                    //If any entity is still updating wait 1 ms
                    if (!entity.isDone()) {
//                        Log.i(entity.getClass().getSimpleName() + " is not done updating...");
                        done = false;
                        break;
                    }
                }
                if (!done)
                    try {
                        syncObject.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
