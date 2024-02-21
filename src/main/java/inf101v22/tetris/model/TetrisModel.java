package inf101v22.tetris.model;

import inf101v22.tetris.controller.TetrisControllabe;

import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.view.TetrisViewable;
import inf101v22.grid.CoordinateItem;
import inf101v22.grid.Coordinate;


public class TetrisModel implements TetrisViewable, TetrisControllabe{
    private TetrisBoard board;
    private PositionedPiece piece;
    private PositionedPieceFactory pieceFactory;
    private GameScreen tilstandTilSpillet = GameScreen.WELCOME_SCREEN;
    public int poengSum; 
    private int pieceCount;

    public TetrisModel(){

        //Bestemmer dimensjonen på selve brettet her
        this.board = new TetrisBoard(15,10);

        //Her blir hjørnene gitt en farge i henhold til oppgavebeskrivelsen 
        // board.set(new Coordinate(0,0), new Tile(Color.GREEN,'h'));
        // board.set(new Coordinate(0,9), new Tile(Color.YELLOW,'e'));
        // board.set(new Coordinate(14,0), new Tile(Color.RED,'r'));
        // board.set(new Coordinate(14,9), new Tile(Color.BLUE,'i'));

        //Henter ut en ny brikke 
        getNewPositionedPiece();
    }

    @Override
    public int getrows(){
        return board.getRows();
    }

    @Override
    public int getcols(){
        return board.getCols();
    }

    public Iterable<CoordinateItem<Tile>> iterateBoard(){
        return board;
    }

    public Iterable<CoordinateItem<Tile>> iterateFallingPiece(){
        return piece;
    }

    

    /**
     * Sjekker om en brikke befinner seg på en lovlig posisjon
     * @param piece
     * @return
     */
    private boolean legalPosition(PositionedPiece piece) {
        try {
            for (CoordinateItem<Tile> tileItem : piece) {
                Coordinate coordinate = tileItem.coordinate;
                if (board.get(coordinate) != null) {
                    return false;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Metode som sjekker om kopien til den forflyttede kopien befinner seg
     * på en lovlig plass
     * @return boolean (om den kopien står på lovlig plass eller ei)
     */
    public boolean moveFallingPiece(int deltaRow, int deltaCol){

        PositionedPiece kandidatPositionedPiece = piece.movedPositionedPieceCopy(deltaRow,deltaCol);
        
        if (legalPosition(kandidatPositionedPiece)) {
            piece = kandidatPositionedPiece;
            return true;
        }
        return false;
    }

    /**
     * Sjekker om den roterte brikken har gyldig plassering
     * @return boolean (om den roterte brikken har lov til å være der eller ei)
     */
    public boolean rotateFallingPiece(){
        PositionedPiece kandidatPositionedPiece = piece.rotatedPositionedPieceCopy();
        
        if (legalPosition(kandidatPositionedPiece)) {
            piece = kandidatPositionedPiece;
            return true;
        }
        return false;
    }

    public void dropFallingPiece(){
        stickFallingPiece();
    }

    //Her lager jeg en brikke som blir plassert midt på brettet på øverste rad (rad 0)
    private PositionedPiece getNewPositionedPiece(){
        pieceFactory = new PositionedPieceFactory();
        pieceFactory.setCenterColumn(board.getCols()/2);
        this.piece = pieceFactory.getNextPositionedPiece();
        this.pieceCount++;
        return piece;
        
    }

    //Hjelpemetode som skal feste de fallende brikkene 
    private void stickFallingPiece(){
        /*
        Looper over flisene i brikka også fester dem på brettet en etter en
        Deretter kaller jeg på getNewPositionedPiece så får jeg den nye brikka opp med en gang 
        */
        for(CoordinateItem<Tile> item : piece){
            board.set(item.coordinate,item.item);
        }

        PositionedPiece newPiece = getNewPositionedPiece();
        

        if(legalPosition(newPiece)==false){
            setGameScreen(GameScreen.GAME_OVER);
        }
        this.poengSum += Math.pow(board.explodeRows(), 2);
    
    }

    public GameScreen getGameScreen(){
        return this.tilstandTilSpillet;
    }

    public void setGameScreen(GameScreen gameScreen){
        this.tilstandTilSpillet = gameScreen;
    }


    public int millisecondsBetweenPieces(){
        int startSpeed = 3000;
        int newSpeed;
        newSpeed = (int) Math.round(startSpeed*Math.pow(0.98, pieceCount));
        return newSpeed;
    }

    public void clockTick(){
        if (moveFallingPiece(1, 0)){
        }
        else {
            stickFallingPiece();
        }
    }
}
 