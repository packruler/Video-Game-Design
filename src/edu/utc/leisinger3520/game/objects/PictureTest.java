package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created by Ethan Leisinger (aka Packruler) on 8/26/2015.
 */
public class PictureTest extends Entity {
    private Rectangle box;
    private Texture texture;
    private float wr;
    private float hr;

    public PictureTest(int width, String pngPath) {
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(pngPath));
            wr = (1.0f) * texture.getImageWidth() / texture.getImageHeight();
            hr = (1.0f) * texture.getImageHeight() / texture.getImageWidth();
            this.width = width;
            this.height = (int) (width * hr);
            box = new Rectangle(0, 0, width, (int) (width * hr));
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e("Cannot open resource " + pngPath);
        }
    }

    @Override
    public void draw() {
        float x = (float) box.getX();
        float y = (float) box.getY();
        float w = (float) box.getWidth();
        float h = (float) box.getHeight();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glColor3f(1, 1, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(wr, 0);
        GL11.glVertex2f(x + width, y);
        GL11.glTexCoord2f(wr, hr);
        GL11.glVertex2f(x + width, y + width);
        GL11.glTexCoord2f(0, hr);
        GL11.glVertex2f(x, y + width);

        GL11.glEnd();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
