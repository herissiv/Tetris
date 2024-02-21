package inf101v22.tetris.view;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;

public interface TetrisViewable {

    /**
     * Get number of rows of the tetris model
     * @return number of rows 
     */
    int getrows();

    /**
     * Get number of cols of tetris model
     * @return number of cols 
     */
    int getcols();

    /**
     * iterate over tetris board 
     * @return board 
     */
    Iterable<CoordinateItem<Tile>> iterateBoard();

    /**
     * Iterate over the placed piece 
     * @return piece 
     */
    Iterable<CoordinateItem<Tile>> iterateFallingPiece();

    /**
     * Metode som gir oss tilstanden til spillet, 
     * om det er game over eller om spillet er aktivt
     * @return gamescreen tilstand 
     */
    GameScreen getGameScreen();

}
