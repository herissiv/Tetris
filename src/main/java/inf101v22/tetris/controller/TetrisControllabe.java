package inf101v22.tetris.controller;

import inf101v22.tetris.model.GameScreen;

public interface TetrisControllabe {
    /**
     * Metode som forteller oss om flyttingen faktisk skjer eller ei
     * @param deltaRow
     * @param deltaCol
     * @return boolean 
     */
    boolean moveFallingPiece(int deltaRow, int deltaCol);


    /**
     * Metode for å rotere brikken
     * @return
     */
    boolean rotateFallingPiece();

    /**
     * Metode som slipper brikken 
     */
    void dropFallingPiece();


    /**
     * Metode som gir oss tilstanden til spillet, 
     * om det er game over eller om spillet er aktivt
     * @return gamescreen tilstand 
     */
    GameScreen getGameScreen();

    /**
     * Enderer tilstand til spillet 
     * @param gameScreen  
     */
    void setGameScreen(GameScreen gameScreen);

    /**
     * Teller hvor mange millisekunder mellom hver brikke 
     * @return float (millisekunder mellom hver brikke)
     */
    int millisecondsBetweenPieces();

    void clockTick();
}
