package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.logging.Log;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created by Ethan Leisinger on 8/27/2015.
 */
public class AirplaneScrolling extends Box {
    private final int SCROLL_RIGHT = 0;
    private final int SCROLL_LEFT = 1;
    private final int SCROLL_DOWN = 2;
    private final int SCROLL_UP = 3;

    private int scrollState = SCROLL_RIGHT;
    private org.newdawn.slick.opengl.Texture texture;
    private float wr;
    private float hr;
    //Auto scroll can be toggled by tapping SPACE quick. Arrow keys move plane no matter what.

    public AirplaneScrolling(int width, String pngPath) {
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(pngPath));
            x = 0;
            y = 0;
            wr = (1.0f) * texture.getImageWidth() / texture.getImageHeight();
            hr = (1.0f) * texture.getImageHeight() / texture.getImageWidth();
            float ratio = (float) width / texture.getImageWidth();
            this.width = (int) (texture.getImageWidth() * ratio);
            this.height = (int) (texture.getImageHeight() * ratio);

            init();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e("Cannot open resource " + pngPath);
        }
        autoScroll = true;
    }

    @Override
    public void update(float delta) {
        int distance = (int) (delta * .25);
        switch (scrollState) {
            case SCROLL_RIGHT:
                if (!moveRight(distance))
                    scrollState = SCROLL_DOWN;
                break;
            case SCROLL_DOWN:
                if (!moveDown(distance))
                    scrollState = SCROLL_LEFT;
                break;
            case SCROLL_LEFT:
                if (!moveLeft(distance))
                    scrollState = SCROLL_UP;
                break;
            case SCROLL_UP:
                if (!moveUp(distance))
                    scrollState = SCROLL_RIGHT;
                break;
        }
        updateHitBox();
    }

    @Override
    public void draw() {
//        x = (int) hitbox.getX();
//        y = (int) hitbox.getY();
        float w = (float) hitbox.getWidth();
        float h = (float) hitbox.getHeight();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glColor3f(1, 1, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex2f(x + width, y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + width, y + width);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + width);

        GL11.glEnd();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
