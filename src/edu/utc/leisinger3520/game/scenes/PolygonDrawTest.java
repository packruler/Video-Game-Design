package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.objects.Circle;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class PolygonDrawTest extends Scene {
    private Circle test1 = new Circle(200, 200, 100);

    public PolygonDrawTest() {

    }

    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        test1.draw();
        return false;
    }
}
