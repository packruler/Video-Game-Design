package edu.utc.leisinger3520.game.gridStuff;

import edu.utc.leisinger3520.game.display.Color;
import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class GridCell extends Entity {
    private Color outline = new Color(0, 0, 0);
    private Color fill = new Color();

    public GridCell(int x, int y, int w, int h) {
        hitbox.setBounds(x, y, w, h);
        active = Math.random() >= .5;
    }

    @Override
    public void draw() {
        drawOutline();

        if (active)
            drawFill();
    }

    protected void drawOutline() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glBegin(GL11.GL_QUADS);

        outline.use();

        GL11.glVertex2i(hitbox.x, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
        GL11.glVertex2i(hitbox.x, hitbox.y + hitbox.height);
        GL11.glEnd();
    }

    protected void drawFill() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glBegin(GL11.GL_QUADS);
        fill.use();

        GL11.glVertex2i(hitbox.x, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
        GL11.glVertex2i(hitbox.x, hitbox.y + hitbox.height);
        GL11.glEnd();
    }
}
