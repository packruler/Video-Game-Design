package edu.utc.leisinger3520.game.objects;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.Font;

/**
 * Created by packruler on 11/23/15.
 */
public class ScoreBoard extends Entity {
    private int left, right;

    public ScoreBoard() {

    }

    public void scoreLeft() {
        left++;
    }

    public int getLeft() {
        return left;
    }

    public void scoreRight() {
        right++;
    }

    public int getRight() {
        return right;
    }

    public void reset() {
        left = 0;
        right = 0;
    }

    @Override
    public synchronized void draw() {
        TrueTypeFont font = new TrueTypeFont(new Font("Ariel", Font.BOLD, 50), true);
        font.drawString(0, 0, "" + left, Color.black);
        font.drawString(Display.getWidth() - 40, 0, "" + right, Color.black);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
