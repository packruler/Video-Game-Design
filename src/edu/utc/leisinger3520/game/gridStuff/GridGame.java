package edu.utc.leisinger3520.game.gridStuff;

import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class GridGame extends Scene {
    public int colCount = 100;
    public int rowCount = 100;
    public GridCell[][] grid = new GridCell[colCount][rowCount];
    public final int cellHeight, cellWidth;
    private GridCell player = null;
    private GridCell target = null;
    public LinkedList<GridCell> path = new LinkedList<>();

    public GridGame() {
        cellHeight = Display.getHeight() / rowCount;
        cellWidth = Display.getWidth() / colCount;
        int status;

        for (int y = 0; y < rowCount; y++)
            for (int x = 0; x < colCount; x++) {
                if (Math.random() < .3)
                    status = 1;
                else status = 0;
                grid[x][y] = new GridCell(x, y, this, status);
            }

        int x = (int) (Math.random() * colCount);
        int y = (int) (Math.random() * rowCount);
        grid[x][y].setStatus(GridCell.PLAYER);
        player = grid[x][y];

        while (target == null) {
            x = (int) (Math.random() * colCount);
            y = (int) (Math.random() * rowCount);
            if (grid[x][y].getStatus() == GridCell.EMPTY) {
                grid[x][y].setStatus(GridCell.TARGET);
                target = grid[x][y];
            }
        }

        player.findTarget();
    }

    @Override
    public boolean drawFrame(float delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

        for (int y = 0; y < rowCount; y++)
            for (int x = 0; x < colCount; x++)
                grid[x][y].draw();


        return false;
    }
}
