package inf101v22.tetris.view;
import javax.swing.JComponent;


import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.model.Tile;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;


public class TetrisView extends JComponent {
    {
        this.setFocusable(true);
    }
    
    public TetrisModel tetrisModel;
    
    public TetrisView(TetrisModel tetrisModel) {
        this.tetrisModel = tetrisModel; 
        
    }
    @Override 
    public void paint(Graphics canvas) {
        super.paint(canvas);
        int m = 3;
        drawTetrisBoard(canvas, m, m, this.getWidth() - 2 * m, this.getHeight() - 2 * m, m, this.tetrisModel.iterateBoard());
        drawTetrisBoard(canvas, m, m, this.getWidth() - 2 * m, this.getHeight()- 2 * m, m, this.tetrisModel.iterateFallingPiece());
        
        if (tetrisModel.getGameScreen()==GameScreen.GAME_OVER){
            canvas.setColor(new Color(0,0,0,128));
            canvas.fillRect(0, 0, 2000, 1000);
            
            canvas.setColor(Color.WHITE);
            GraphicHelperMethods.drawCenteredString(
                canvas, "GAME OVER",
                20, 250, this.getWidth() - 40, this.getHeight() - 540);
            GraphicHelperMethods.drawCenteredString(
                canvas, Integer.toString(tetrisModel.poengSum), 
                20, 300, this.getWidth()-40, this.getHeight()-540);
        }
        else if (tetrisModel.getGameScreen() == GameScreen.WELCOME_SCREEN) {
            canvas.setColor(new Color(0,0,0,128));
            canvas.fillRect(0, 0, 2000, 1000);
            
            canvas.setColor(Color.WHITE);
            GraphicHelperMethods.drawCenteredString(
                    canvas, "Welcome to Tetris",
                    20, 250, this.getWidth() - 40, this.getHeight() - 540);
            GraphicHelperMethods.drawCenteredString(    
                    canvas, "Press key down to start",
                    20, 300, this.getWidth() - 40, this.getHeight() - 540);

        }
    }

    
    public void drawTileWithRightBottomPadding(Graphics canvas, int x, int y, int xWidth, int yHeight, int padding, Color farge) {
        canvas.setColor(farge);
        canvas.fillRect(x, y, xWidth-padding, yHeight-padding);
    }

    public void drawBoardWithRightBottomPadding(Graphics canvas, int x, int y, int xWidth, int yHeight, int padding, Iterable<CoordinateItem<Tile>> tiles) {
        int rows = this.tetrisModel.getrows();
        int cols = this.tetrisModel.getcols();

        for (CoordinateItem<Tile> coordinateItem : tiles){
            Color color = Color.BLACK;
            Tile tile = coordinateItem.item;
            int row = coordinateItem.coordinate.row;
            int col = coordinateItem.coordinate.col;
            int x1 = x + col*xWidth/cols;
            int nextX1 = x + (col + 1)*xWidth/cols;
            int y1 = y + row*yHeight/rows;
            int nextY1 = y + (row + 1)*yHeight/rows;
            int cellWidth = nextX1 - x1;
            int cellHeight = nextY1 - y1;
            if (tile == null) {
                drawTileWithRightBottomPadding(canvas, x1, y1, cellWidth, cellHeight, padding, color);
            }   
            else {
                color = tile.farge;
                drawTileWithRightBottomPadding(canvas, x1, y1, cellWidth, cellHeight, padding, color);
            }         

        }
    }
    public void drawTetrisBoard(Graphics canvas, int x, int y, int xWidth, int yHeight, int padding, Iterable<CoordinateItem<Tile>> tiles){
        drawBoardWithRightBottomPadding(canvas, x*padding, y*padding, xWidth-padding, yHeight-padding, padding, this.tetrisModel.iterateBoard());
        drawBoardWithRightBottomPadding(canvas, x*padding, y*padding, xWidth-padding, yHeight-padding, padding, this.tetrisModel.iterateFallingPiece());

    }
    
    @Override
    public Dimension getPreferredSize() {
        int width = 450;
        int height = 600;
        return new Dimension(width, height);
    }


}
