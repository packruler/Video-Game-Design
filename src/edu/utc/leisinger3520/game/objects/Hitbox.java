package edu.utc.leisinger3520.game.objects;

import java.awt.*;

/**
 * Created by packr on 11/6/2015.
 */
public class Hitbox extends Rectangle {
    public Hitbox() {

    }

    public Hitbox(int x, int y, int height, int width) {
        super(x, y, width, height);
    }

    public void setX(int x) {
        this.setLocation(x, (int) getY());
    }


    public void setY(int y) {
        this.setLocation((int) getX(), y);
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }
}
