package edu.utc.leisinger3520.game.objects;

import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class Circle extends Entity {
    private int numSegments = 360;

    public Circle(int x, int y, int radius) {
        hitbox.setRect(x, y, radius * 2, radius * 2);
    }

    @Override
    public synchronized void draw() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

        float radius = (float) (hitbox.getWidth() / 2);
        float x = (float) (hitbox.getX() + radius);
        float y = (float) (hitbox.getY() + radius);


//        GL11.glPushMatrix();
//        GL11.glTranslatef(x, y, 0);
//        GL11.glScalef(radius, radius, 1);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glColor3f(0, 0, 0);

        GL11.glVertex2f(x, y); // center of circle
        double twicePi = 2 * Math.PI;
        for (int i = 0; i <= numSegments; i++) {
            GL11.glVertex2f(
                    (float) (x + (radius * Math.cos(i * twicePi / numSegments))),
                    (float) (y + (radius * Math.sin(i * twicePi / numSegments)))
            );
        }

        GL11.glEnd();
    }
}
