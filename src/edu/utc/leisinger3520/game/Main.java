package edu.utc.leisinger3520.game;

import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.scenes.HockeyGame;
import edu.utc.leisinger3520.game.scenes.PauseScreen;
import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.Font;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class Main {
    public static final int TARGET_FPS = 60;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;
    private static boolean SHUTDOWN = false;
    private static boolean PAUSED = false;
    private static boolean GAME_OVER = false;


    public static Scene scene;

    public static void main(String[] args) throws LWJGLException {
        initGL(SCR_WIDTH, SCR_HEIGHT);

        long now = System.currentTimeMillis();
        long delta;

        scene = new HockeyGame();
        PauseScreen pauseScreen = new PauseScreen();
        long escapePress = 0;


        while (!Display.isCloseRequested() && !SHUTDOWN) {

            // UPDATE DISPLAY
            Display.update();
            Display.sync(TARGET_FPS);
            delta = System.currentTimeMillis() - now;
            now = System.currentTimeMillis();

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && now - escapePress > 500)
                PAUSED = !PAUSED;

            // DRAW OBJECTS
            if (scene.drawFrame(delta, !PAUSED && !GAME_OVER))
                scene = new HockeyGame();

            if (PAUSED)
                PAUSED = pauseScreen.drawFrame(delta, false);

            if (GAME_OVER)
                PAUSED = true;

            if (HockeyGame.SCORE_BOARD.getLeft() >= 5) {
                GAME_OVER = true;

                TrueTypeFont font = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 50), true);
                font.drawString(Display.getWidth() / 2 + 50, Display.getHeight() / 2, "Player Left Wins!", Color.black);

                GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            } else if (HockeyGame.SCORE_BOARD.getRight() >= 5) {
                GAME_OVER = true;

                TrueTypeFont font = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 50), true);
                font.drawString(Display.getWidth() / 2 + 50, Display.getHeight() / 2, "Player Right Wins!", Color.black);

                GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            }


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

    public static void shutdown() {
        SHUTDOWN = true;
    }

    public static void restartGame() {
        GAME_OVER = false;
        HockeyGame.SCORE_BOARD.reset();
        resetGame();
    }

    public static void resetGame() {
        scene = new HockeyGame();
    }
}
