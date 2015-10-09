package edu.utc.leisinger3520.game.display;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.InputStream;

/**
 * Created by Ethan Leisinger on 9/17/2015.
 */
public class FramerateDisplay extends Entity {

    private int fps = 0;
    private static TrueTypeFont font;
    private boolean skipFrame = false;


    public FramerateDisplay() {
        width = 100;
        x = Display.getWidth() - width;
        y = 0;
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

    @Override
    public void update(float delta) {
        fps = (int) Math.floor(1000 / delta);
    }

    @Override
    public synchronized void draw() {
        if (skipFrame)
            skipFrame = false;
        else {
            TextureImpl.unbind();
            String display = "FPS: " + fps;
            font.drawString(x, y, display, Color.black);
            TextureImpl.getLastBind();
        }
    }
}
