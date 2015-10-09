package edu.utc.leisinger3520.game.scenes;

import edu.utc.leisinger3520.game.display.FramerateDisplay;
import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.objects.projectiles.Projectile;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger (aka Packruler) on 9/9/2015.
 */
public class MainGame extends Scene {

    private static MainGame instance;

    public final LinkedList<Entity> ENTITIES = new LinkedList<>();
    public final LinkedList<Entity> PROJECTILES = new LinkedList<>();
    private final FramerateDisplay FPS_DISPLAY = new FramerateDisplay();

    public static MainGame getInstance() {
        if (instance == null)
            instance = new MainGame();
        return instance;
    }

    public void addEntity(Entity entity) {
        synchronized (ENTITIES) {
            ENTITIES.add(entity);
        }
    }

    public void addProjectile(Projectile projectile) {
        synchronized (PROJECTILES) {
            PROJECTILES.add(projectile);
        }
    }


    public void update(float delta) {
        updateEntities(delta);
        updateProjectiles(delta);

        runHitChecks();

        FPS_DISPLAY.update(delta);
    }

    public void updateEntities(float delta) {
        synchronized (ENTITIES) {
            for (Entity entity : ENTITIES) {
                entity.update(delta);
            }
        }
    }

    public void updateProjectiles(float delta) {
        synchronized (PROJECTILES) {
            Iterator<Entity> iterator = PROJECTILES.listIterator();

            while (iterator.hasNext()) {
                Entity projectile = iterator.next();

                if (!projectile.isActive())
                    iterator.remove();
                else
                    projectile.update(delta);
            }
        }
    }

    public boolean drawFrame(float delta) {
        update(delta);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        synchronized (ENTITIES) {
            for (Entity entity : ENTITIES) {
                entity.draw();
            }
        }

        synchronized (PROJECTILES) {
            for (Entity projectile : PROJECTILES) {
                projectile.draw();
            }
        }

        FPS_DISPLAY.draw();

        return true;
    }

    public void runHitChecks() {

    }
}
