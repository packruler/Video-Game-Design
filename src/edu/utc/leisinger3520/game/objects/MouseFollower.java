package edu.utc.leisinger3520.game.objects;

import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.projectiles.Projectile;
import edu.utc.leisinger3520.game.scenes.MainGame;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Created by Ethan Leisinger (aka Packruler) on 8/26/2015.
 */
public class MouseFollower extends Entity {
    private Texture texture;
    private float wr;
    private float hr;

    private long lastLeftPress = 0;
    private long lastRightPress = 0;
    private long keyPressDelay = 500;

    public MouseFollower(int width, String pngPath) {
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(pngPath));
            wr = (1.0f) * texture.getImageWidth() / texture.getImageHeight();
            hr = (1.0f) * texture.getImageHeight() / texture.getImageWidth();
            hitbox.setRect(300, 300, width, width * hr);

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.e("Cannot open resource " + pngPath);
        }
    }

    @Override
    public void update(float delta) {
        if (Mouse.isButtonDown(0)) {
            int mx = (int) (Mouse.getX() - (getWidth() / 2));
            int my = (int) (Display.getHeight() - Mouse.getY() - (getHeight() / 2));

            setX((int) ((mx - getX()) * .01 * delta));
            setY((int) ((my - getY()) * .01 * delta));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            shoot(Keyboard.KEY_LEFT);
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            shoot(Keyboard.KEY_RIGHT);
        shoot(Keyboard.KEY_UP);
    }

    @Override
    public void draw() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glColor3f(1, 1, 0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2d(getX(), getY());
        GL11.glTexCoord2d(1, 0);
        GL11.glVertex2d(getX() + getWidth(), getY());
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2d(getX() + getWidth(), getY() + getHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2d(getX(), getY() + getHeight());

        GL11.glEnd();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    private void shoot(int key) {
        switch (key) {
            case Keyboard.KEY_LEFT:
                if (System.currentTimeMillis() - lastLeftPress > keyPressDelay) {
                    MainGame.getInstance().addProjectile(new Projectile((int) getX() - Projectile.WIDTH, (int) (getY() + (getHeight() / 2) - (Projectile.WIDTH / 2)), -Projectile.VELOCITY, 0));
                    lastLeftPress = System.currentTimeMillis();
                }
                break;
            case Keyboard.KEY_RIGHT:
                if (System.currentTimeMillis() - lastRightPress > keyPressDelay) {
                    MainGame.getInstance().addProjectile(new Projectile((int) (getX() + getWidth()), (int) (getY() + (getHeight() / 2) - (Projectile.WIDTH / 2)), Projectile.VELOCITY, 0));
                    lastRightPress = System.currentTimeMillis();
                }
                break;
        }
    }
}
