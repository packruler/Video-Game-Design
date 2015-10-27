package edu.utc.leisinger3520.game.gridStuff;

import edu.utc.leisinger3520.game.display.Color;
import edu.utc.leisinger3520.game.logging.Log;
import edu.utc.leisinger3520.game.objects.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ethan Leisinger on 10/21/2015.
 */
public class GridCell extends Entity {
    private Color outline = new Color(0, 0, 0);
    private Color fill = new Color();
    private int status;
    public static final int EMPTY = 0;
    public static final int OBSTACLE = 1;
    public static final int PLAYER = 2;
    public static final int TARGET = 3;
    private int distanceFromPlayer = 0;
    private boolean found = false;
    private int row, col, fromRow, fromCol;
    private final GridGame game;
    private static final Color onPath = new Color(.5, .5, 1);

    public GridCell(int col, int row, GridGame game, int status) {
        hitbox.setBounds(col * game.cellWidth, row * game.cellHeight, game.cellWidth, game.cellHeight);
        this.col = col;
        this.row = row;
        this.game = game;
        setStatus(status);
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
            case EMPTY:
                fill = new Color(1, 1, 1);
                break;
            case OBSTACLE:
                fill = new Color(0, 0, 0);
                break;
            case PLAYER:
                fill = new Color(0, 1, 0);
                break;
            case TARGET:
                fill = new Color(1, 0, 0);
                break;
        }
    }

    public void findTarget() {
        if (row - 1 >= 0)
            checkNext(col, row - 1);

        if (col - 1 >= 0)
            checkNext(col - 1, row);

        if (row + 1 < game.rowCount)
            checkNext(col, row + 1);

        if (col + 1 < game.colCount)
            checkNext(col + 1, row);
    }

    public void checkNeighbors(final int fromCol, final int fromRow, int distance) {
        found = true;
        distance++;
//        Log.i("Row: " + row + " Col: " + col + " Dist: " + distanceFromPlayer);
//        Log.i("Current distance: " + distanceFromPlayer + "New distance: " + distance);
        if (status == TARGET) {
            Log.i("TARGET FOUND From: " + fromCol + "," + fromRow + " Dist: " + distance);
            if (distanceFromPlayer == 0 || distanceFromPlayer > distance) {
                Log.i("Update path");
                this.fromCol = fromCol;
                this.fromRow = fromRow;
                distanceFromPlayer = distance;
                game.path.clear();
                reversePath();
            }
        } else if (distanceFromPlayer == 0 || distanceFromPlayer > distance) {
            distanceFromPlayer = distance;
            this.fromCol = fromCol;
            this.fromRow = fromRow;
            if (row - 1 >= 0)
                checkNext(col, row - 1);

            if (col - 1 >= 0)
                checkNext(col - 1, row);

            if (row + 1 < game.rowCount)
                checkNext(col, row + 1);

            if (col + 1 < game.colCount)
                checkNext(col + 1, row);
        }
    }

    private void checkNext(int x, int y) {
        GridCell nextCell = game.grid[x][y];
        if (!(nextCell.isFound() && nextCell.distanceFromPlayer < distanceFromPlayer) && nextCell.getStatus() != OBSTACLE) {
            nextCell.checkNeighbors(col, row, distanceFromPlayer);
        }
    }

    private void reversePath() {
        if (status == EMPTY)
            game.path.add(this);

        if (status != PLAYER)
            game.grid[fromCol][fromRow].reversePath();
    }

    public boolean isFound() {
        return found;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public void draw() {
        drawOutline();

        drawFill();
    }

    protected void drawOutline() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glBegin(GL11.GL_QUADS);

        outline.use();

        GL11.glVertex2i(hitbox.x, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
        GL11.glVertex2i(hitbox.x, hitbox.y + hitbox.height);
        GL11.glEnd();
    }

    protected void drawFill() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glBegin(GL11.GL_QUADS);

        if (game.path.contains(this))
            onPath.use();
        else
            fill.use();

        GL11.glVertex2i(hitbox.x, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y);
        GL11.glVertex2i(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
        GL11.glVertex2i(hitbox.x, hitbox.y + hitbox.height);
        GL11.glEnd();
    }
}
