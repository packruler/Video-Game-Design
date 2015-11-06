package edu.utc.leisinger3520.game.gridStuff;

import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.scenes.Scene;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

//        player.findTarget();
        for (GridCell cell : findPath(player, target)) {
            cell.setPartOfPath();
        }
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

    public Set<GridCell> getNeighbors(GridCell cell) {
        Set<GridCell> set = new TreeSet<>();

        if (cell.getColumn() > 0
                && grid[cell.getColumn() - 1][cell.getRow()].getStatus() != GridCell.OBSTACLE)
            set.add(grid[cell.getColumn() - 1][cell.getRow()]);

        if (cell.getRow() > 0
                && grid[cell.getColumn()][cell.getRow() - 1].getStatus() != GridCell.OBSTACLE)
            set.add(grid[cell.getColumn()][cell.getRow() - 1]);

        if (cell.getColumn() + 1 < colCount
                && grid[cell.getColumn() + 1][cell.getRow()].getStatus() != GridCell.OBSTACLE)
            set.add(grid[cell.getColumn() + 1][cell.getRow()]);

        if (cell.getRow() + 1 < rowCount
                && grid[cell.getColumn()][cell.getRow() + 1].getStatus() != GridCell.OBSTACLE)
            set.add(grid[cell.getColumn()][cell.getRow() + 1]);

        return set;
    }

    public List<GridCell> findPath(GridCell from, GridCell to) {
        LinkedList<GridCell> needToVisit = new LinkedList<>();
        from.setPathCost(0);

        needToVisit.addAll(getNeighbors(from));

        GridCell current;
        while (!needToVisit.isEmpty()) {
            current = needToVisit.pollFirst();
            if (!current.equals(to) && !current.equals(from))
                for (GridCell cell : getNeighbors(current)) {
                    if (!cell.setVisited(current))
                        needToVisit.add(cell);
                }
        }

        LinkedList<GridCell> path = new LinkedList<>();

        if (to.getPreviousCell() != null) {
            current = to;
            while (current.getPreviousCell() != null) {
                current = current.getPreviousCell();
                path.add(current);
            }
        }

        return path;
    }
}
