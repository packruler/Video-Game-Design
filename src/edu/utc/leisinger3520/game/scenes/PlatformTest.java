package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.objects.characters.Jumper;
import edu.utc.leisinger3520.game.objects.ground.Platform;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 10/7/2015.
 */
public class PlatformTest extends Scene {
    private LinkedList<Platform> platforms = new LinkedList<>();
    private Jumper jumper = new Jumper();

    public PlatformTest() {
        platforms.add(new Platform(50, 100, 500, 500));
        platforms.add(new Platform(50, 100, 700, 300));
        platforms.add(new Platform(2 * Display.getHeight(), Display.getWidth(), 0, -2 * Display.getHeight()));
        platforms.add(new Platform(Display.getHeight() * 2, Display.getWidth(), 0, Display.getHeight()));
        platforms.add(new Platform(Display.getHeight() * 5, Display.getWidth() * 2, Display.getWidth() * -2, Display.getHeight() * -2));
        platforms.add(new Platform(Display.getHeight() * 5, Display.getWidth() * 2, Display.getWidth(), Display.getHeight() * -2));

    }

    private float zoom = 10;

    @Override
    public boolean drawFrame(float delta) {
//        Log.i("FPS: " + ((int) Math.floor(1000 / delta)));

        if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) {
            zoom -= 0.1f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
            zoom += 0.1f;
        }

        jumper.update(delta);

        //            platform.update(delta);
        platforms.stream().filter(jumper::isCollision).forEach(jumper::onCollision);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(jumper.getX() - (160 * zoom), jumper.getX() + (160 * zoom),
                jumper.getY() + (90 * zoom), jumper.getY() - (90 * zoom),
                1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        jumper.draw();
        for (Platform platform : platforms) {
            platform.draw();
        }

        return true;
    }
}
