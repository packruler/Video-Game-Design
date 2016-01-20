package edu.utc.leisinger3520.game.display;

import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.awt.*;
import java.awt.Font;

/**
 * Created by Ethan Leisinger on 11/25/15.
 */
public class MenuText extends Entity {
    private int x, y, fontSize;
    private String text;
    private boolean selected;

    public MenuText(int x, int y, String text, int fontSize) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.fontSize = fontSize;
    }

    public void setSelected(boolean isSelected) {
        selected = isSelected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public synchronized void draw() {
        TrueTypeFont font = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, fontSize), true);
        Color color;

        if (selected)
            color = Color.red;
        else color = Color.black;

        font.drawString(x, y, text, color);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
