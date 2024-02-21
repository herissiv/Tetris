package inf101v22.tetris.controller;
import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.view.TetrisView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TetrisController implements java.awt.event.KeyListener, java.awt.event.ActionListener{
    private TetrisControllabe tetrisModel;
    private TetrisView tetrisView;
    private TetrisSong tetrisSong = new TetrisSong();
    private Timer timer;
    
    public TetrisController(TetrisControllabe tetrisModel, TetrisView tetrisView){
        this.tetrisModel = tetrisModel;
        this.tetrisView = tetrisView;
        tetrisView.addKeyListener(this);

        this.tetrisSong.run();

        timer = new Timer(tetrisModel.millisecondsBetweenPieces(), this);
        this.timer.start();

    }

    public void keyTyped(KeyEvent e){
        
    }
    
    public void keyPressed(KeyEvent e){
        if (tetrisModel.getGameScreen() == GameScreen.WELCOME_SCREEN){
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                tetrisModel.setGameScreen(GameScreen.ACTIVE_GAME);
            }
        }
        if (tetrisModel.getGameScreen() == GameScreen.ACTIVE_GAME){

            // JFrame frame = new JFrame("INF101 Tetris, SCORE: "+tetrisModel.poengSum);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.pack();
            // frame.setVisible(true);

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                // Left arrow was pressed
                tetrisModel.moveFallingPiece(0, -1);
                tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Right arrow was pressed
                tetrisModel.moveFallingPiece(0, 1);
                tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                tetrisModel.moveFallingPiece(1, 0);
                tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed

                //Brikken flytter seg ikke hvis man trykker piltast opp
                tetrisModel.rotateFallingPiece();
                tetrisView.repaint();
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Spacebar was pressed

                /*
                Denne loopen får brikken til å falle ned til nederste lovlige rad, 
                velger 15 siden lengden på brettet er 15 og maks antall rader den 
                kan flyttes er 14
                */
                for (int i = 0; i<15; i++){
                    tetrisModel.moveFallingPiece(1, 0);
                    tetrisView.repaint();
                }
                
                tetrisModel.dropFallingPiece();
                tetrisView.repaint();
            }
        }   

    }

    public void keyReleased(KeyEvent e){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (tetrisModel.getGameScreen()==GameScreen.ACTIVE_GAME){
            tetrisModel.clockTick();
            tetrisView.repaint();
            rightDelay();
        }
        
    }
    
    public void rightDelay(){
        timer.setDelay(tetrisModel.millisecondsBetweenPieces());
        timer.setInitialDelay(3000);
    }
}
