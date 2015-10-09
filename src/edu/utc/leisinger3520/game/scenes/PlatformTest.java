package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.objects.characters.Jumper;
import edu.utc.leisinger3520.game.objects.ground.Platform;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 10/7/2015.
 */
public class PlatformTest extends Scene {
    private LinkedList<Platform> platforms = new LinkedList<>();
    private Jumper jumper = new Jumper();

    public PlatformTest() {
        platforms.add(new Platform(50, 100, 500, 800));

    }

    @Override
    public boolean drawFrame(float delta) {
        jumper.update(delta);
        for (Platform platform : platforms) {
            platform.update(delta);
            if (jumper.intersects(platform))
                jumper.onCollision(platform);
        }

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        jumper.draw();
        for (Platform platform : platforms) {
            platform.draw();
        }

        return true;
    }
}
