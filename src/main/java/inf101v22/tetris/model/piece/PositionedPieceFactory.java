package inf101v22.tetris.model.piece;

import java.util.Random;

import inf101v22.grid.Coordinate;

public class PositionedPieceFactory {
    
    private PieceShape[] shapes = PieceShape.STANDARD_TETRIS_PIECES;
    private int centCol;
    Random random = new Random();

    //Lager en metode som bestemmer kolonnekoordinaten til den nye brikken
    public void setCenterColumn(int col){
        this.centCol = col;
    }

    //Lager en metode som generer en tileldig brikke 
    public PositionedPiece getNextPositionedPiece(){
        int next = random.nextInt(shapes.length);
        PieceShape nextShape = shapes[next];

        /*
        Setter radkoordinaten til den nye brikka til å være 0, 
        slik at brikka dukker opp der, og posisjonerer brikken til å havne midt på
        */
        Coordinate nextCoor = new Coordinate(0, centCol-nextShape.getWidth()/2);
        PositionedPiece nextPosPiece = new PositionedPiece(nextShape,nextCoor);
        return nextPosPiece;
    }
}
