package edu.utc.leisinger3520.game.display;

import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class Color {
    private double r, g, b, alpha;

    public Color() {
        r = Math.random();
        g = Math.random();
        b = Math.random();
        alpha = 1;
    }

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
        alpha = 1;
    }

    public Color(double r, double g, double b, double alpha) {
        this(r, g, b);
        this.alpha = alpha;
    }

    public void set(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void use() {
        GL11.glColor4d(r, g, b, alpha);
    }
}
