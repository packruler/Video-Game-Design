package edu.utc.leisinger3520.game.gridStuff;

import edu.utc.leisinger3520.game.objects.Entity;
import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class GridGame extends Scene {
    private int xSize = 10;
    private int ySize = 10;
    private Entity[][] grid = new Entity[xSize][ySize];
    private int cellHeight, cellWidth;

    public GridGame() {
        int yLoc = 0;
        int xLoc = 0;
        cellHeight = Display.getHeight() / ySize;
        cellWidth = Display.getWidth() / xSize;
        for (int y = 0; y < ySize; y++) {
            xLoc = 0;
            for (int x = 0; x < xSize; x++) {
                grid[x][y] = new GridCell(xLoc, yLoc, cellWidth, cellHeight);
                xLoc += cellWidth;
            }
            yLoc += cellHeight;
        }
    }

    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                grid[x][y].draw();
            }
        }
        return false;
    }
}
