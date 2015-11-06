package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.objects.characters.Player;
import edu.utc.leisinger3520.game.objects.characters.PlayerLeft;
import edu.utc.leisinger3520.game.objects.characters.PlayerRight;
import edu.utc.leisinger3520.game.objects.characters.Puck;
import edu.utc.leisinger3520.game.objects.ground.Goal;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 11/2/2015.
 */
public class HockeyGame extends Scene {
    private Player playerLeft = new PlayerLeft();
    private Player playerRight = new PlayerRight();
    private Puck puck = new Puck();
    private LinkedList<Entity> objects = new LinkedList<>();

    private long lastSpace, lastLeft, lastRight, lastUp, lastDown;
    private long keyDelay = 100;
    private HashMap<Integer, Long> keyPress = new HashMap<>(9);
    private boolean vsAI = false;


    public HockeyGame() {
        keyPress.put(Keyboard.KEY_SPACE, 0L);
        keyPress.put(Keyboard.KEY_LEFT, 0L);
        keyPress.put(Keyboard.KEY_RIGHT, 0L);
        keyPress.put(Keyboard.KEY_UP, 0L);
        keyPress.put(Keyboard.KEY_DOWN, 0L);
        keyPress.put(Keyboard.KEY_A, 0L);
        keyPress.put(Keyboard.KEY_D, 0L);
        keyPress.put(Keyboard.KEY_W, 0L);
        keyPress.put(Keyboard.KEY_S, 0L);

        objects.add(playerLeft);
        objects.add(playerRight);
        objects.add(puck);
        objects.add(new Goal(false));
        objects.add(new Goal(true));
    }

    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        keyCheck();

        if (puck.isCollision(playerLeft))
            puck.onCollision(playerLeft);
        else if (puck.isCollision(playerRight))
            puck.onCollision(playerRight);
        int result = puck.isGoal();


        for (Entity current : objects) {
            current.update(delta);
            current.draw();
        }
        if (result == -1)
            Log.i("Goal Left");
        else if (result == 1)
            Log.i("Goal Right");

        return false;
    }

    private void keyCheck() {
        long now = System.currentTimeMillis();
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && now - keyPress.get(Keyboard.KEY_SPACE) > keyDelay) {
            keyPress.put(Keyboard.KEY_SPACE, now);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && now - keyPress.get(Keyboard.KEY_LEFT) > keyDelay) {
            keyPress.put(Keyboard.KEY_LEFT, now);
            playerRight.onMoveLeft();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A) && now - keyPress.get(Keyboard.KEY_A) > keyDelay) {
            keyPress.put(Keyboard.KEY_A, now);
            if (!vsAI)
                playerLeft.onMoveLeft();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && now - keyPress.get(Keyboard.KEY_RIGHT) > keyDelay) {
            keyPress.put(Keyboard.KEY_RIGHT, now);
            playerRight.onMoveRight();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D) && now - keyPress.get(Keyboard.KEY_D) > keyDelay) {
            keyPress.put(Keyboard.KEY_D, now);
            if (!vsAI)
                playerLeft.onMoveRight();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_UP) && now - keyPress.get(Keyboard.KEY_UP) > keyDelay) {
            keyPress.put(Keyboard.KEY_UP, now);
            playerRight.onMoveUp();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_W) && now - keyPress.get(Keyboard.KEY_W) > keyDelay) {
            keyPress.put(Keyboard.KEY_W, now);
            if (!vsAI)
                playerLeft.onMoveUp();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && now - keyPress.get(Keyboard.KEY_DOWN) > keyDelay) {
            keyPress.put(Keyboard.KEY_DOWN, now);
            playerRight.onMoveDown();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S) && now - keyPress.get(Keyboard.KEY_S) > keyDelay) {
            keyPress.put(Keyboard.KEY_S, now);
            if (!vsAI)
                playerLeft.onMoveDown();
        }
    }
}
