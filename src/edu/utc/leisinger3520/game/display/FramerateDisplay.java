package edu.utc.leisinger3520.game.display;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.InputStream;

/**
 * Created by Ethan Leisinger on 9/17/2015.
 */
public class FramerateDisplay {

    private int fps = 0;
    private static TrueTypeFont font;
    private boolean skipFrame = false;
    private static FramerateDisplay instance;


    private FramerateDisplay() {
        if (font == null) {
            // load font from a .ttf file
            try {
                InputStream inputStream = ResourceLoader.getResourceAsStream("./res/font.ttf");

                Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
                awtFont2 = awtFont2.deriveFont(24f); // set font size
                font = new TrueTypeFont(awtFont2, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static FramerateDisplay getInstance() {
        if (instance == null)
            instance = new FramerateDisplay();
        return instance;
    }

    public void update(float delta) {
        fps = (int) Math.floor(1000 / delta);
    }

    public synchronized void draw() {
        if (skipFrame)
            skipFrame = false;
        else {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(Display.getWidth() - 100, Display.getWidth(), Display.getHeight(), 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());

            TextureImpl.unbind();
            String display = "FPS: " + fps;
            font.drawString(0, 0, display, Color.black);
            TextureImpl.getLastBind();
        }
    }
}
