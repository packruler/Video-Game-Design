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

    public Color(double R, double G, double B) {
        r = R;
        g = G;
        b = B;
        alpha = 1;
    }

    public Color(double R, double G, double B, double alpha) {
        this(R, G, B);
        this.alpha = alpha;
    }

    public void set(double R, double G, double B) {
        r = R;
        g = G;
        b = B;
    }

    public void setR(double R) {
        r = R;
    }

    public void setG(double G) {
        g = G;
    }

    public void setB(double B) {
        b = B;
    }

    public void setAlpha(double a) {
        this.alpha = a;
    }


    public void use() {
        GL11.glColor4d(r, g, b, alpha);
    }
}
