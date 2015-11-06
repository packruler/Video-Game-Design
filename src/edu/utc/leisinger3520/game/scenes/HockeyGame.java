package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.objects.Circle;
import edu.utc.leisinger3520.game.objects.characters.ControllableCircle;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class HockeyGame extends Scene {
    private Circle player, opponent, puck;
    private Circle[] objects = new Circle[3];

    private long lastSpace, lastLeft, lastRight, lastUp, lastDown;
    private long keyDelay = 500;

    public HockeyGame() {
        objects[0] = new Circle(Display.getWidth() / 2, Display.getHeight() / 2, 30);
        objects[1] = new ControllableCircle(60, Display.getHeight() / 2, 60);
        objects[2] = new Circle(Display.getWidth() - 60, (Display.getHeight() / 2), 60);

        puck = objects[0];
        player = objects[1];
        opponent = objects[2];
    }

    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        long now = System.currentTimeMillis();

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && now - lastSpace > keyDelay) {

        }
        if (puck.isCollision(player)){
            puck.onCollision(player);
        }
        for (Circle current : objects) {
            current.update(delta);
            current.draw();
        }

        return false;
    }
}
