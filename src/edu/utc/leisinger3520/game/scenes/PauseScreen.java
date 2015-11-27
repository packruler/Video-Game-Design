package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.Main;
import edu.utc.leisinger3520.game.display.MenuText;
import org.lwjgl.input.Keyboard;

/**
 * Created by packruler on 11/25/15.
 */
public class PauseScreen extends Scene {
    private static final int RESUME = 0;
    private static final int EXIT = 3;
    private static final int RESTART = 2;
    private static final int RESET = 1;
    private long downPress, upPress, enterPress;
    private MenuText[] menu = new MenuText[4];
    private int selected = 0;
    private long keyDelay = 500;

    public PauseScreen() {
        int scalar = 100;
        menu[RESUME] = new MenuText(200, 200 + (scalar * RESUME), "RESUME", 50);
        menu[RESUME].setSelected(true);
        menu[EXIT] = new MenuText(200, 200 + (scalar * EXIT), "EXIT", 50);
        menu[RESTART] = new MenuText(200, 200 + (scalar * RESTART), "RESTART", 50);
        menu[RESET] = new MenuText(200, 200 + (scalar * RESET), "RESET", 50);
    }

    @Override
    public boolean drawFrame(float delta, boolean update) {
        for (MenuText current : menu) {
            current.draw();
        }
        return keyCheck();
    }

    private boolean keyCheck() {
        long now = System.currentTimeMillis();
        if (selected < menu.length - 1 &&
                Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_UP) &&
                now - downPress > keyDelay) {
            menu[selected].setSelected(false);
            selected++;
            menu[selected].setSelected(true);
        } else if (selected > 0 &&
                !Keyboard.isKeyDown(Keyboard.KEY_DOWN) && Keyboard.isKeyDown(Keyboard.KEY_UP) &&
                now - upPress > keyDelay) {
            menu[selected].setSelected(false);
            selected--;
            menu[selected].setSelected(true);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
            switch (selected) {
                case RESUME:
                    return false;
                case EXIT:
                    Main.shutdown();
                    return false;
                case RESTART:
                    Main.restartGame();
                    return false;
                case RESET:
                    Main.resetGame();
                    return false;
            }
        }
        return true;
    }
}
