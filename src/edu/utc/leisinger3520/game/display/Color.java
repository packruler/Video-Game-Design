package edu.utc.leisinger3520.game.display;

import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class Color {
    private double r, g, b;

    public Color() {
        r = Math.random();
        g = Math.random();
        b = Math.random();
    }

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void set(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void use() {
        GL11.glColor3d(r, g, b);
    }
}
