package inf101v22.tetris.model.piece;

import java.util.Iterator;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;
import java.util.ArrayList;

public class PositionedPiece implements Iterable<CoordinateItem<Tile>> {
   private PieceShape piece;
   public Coordinate coordinate;

   //Vil lage et objekt med en form med en tilhørende koordinat på brettet 
   PositionedPiece(PieceShape piece, Coordinate coordinate){
       this.piece = piece; 
       this.coordinate = coordinate;
   }
   
   public int getWidth(){
       return piece.getWidth();
   }

   public int getHeight(){
       return piece.getHeight();
   }

   public Tile getTile(){
       return piece.getTile();
   }

   /*
   Metode slik at jeg kan ekstrahere en PieceShape 
   uten å skrive positionPiece.piece (kan bli forvirrende)
   */
   public PieceShape getPiece(){
       return piece;
   }

   public Iterator<CoordinateItem<Tile>> iterator(){

    //Returnerer posisjonen til hver enkelt flis til selve brikka, mao posisjonen til brikka.
    ArrayList<CoordinateItem<Tile>> a = new ArrayList<>();

    //Får dimensjonene til brikka 
    int height = piece.getHeight();
    int width = piece.getWidth();

    for (int i = 0; i<height;i++){
        for (int j = 0; j<width; j++) {
            if (piece.getShape()[i][j] == true){
                Coordinate koor = new Coordinate(i+coordinate.row, j+coordinate.col);
                CoordinateItem<Tile> koorI = new CoordinateItem<Tile>(koor,piece.getTile());
                a.add(koorI);
            }
        }
    }

    return a.iterator();
   }

   //Lager en kopi av en flytta brikke 
   public PositionedPiece movedPositionedPieceCopy(int deltaRow, int deltaCol){

       return new PositionedPiece(this.piece, new Coordinate(this.coordinate.row+deltaRow,this.coordinate.col+deltaCol));
       
   }

   /*
   Roterer brikkene med formlene som ble gitt i oppgaven.
   PS: Det er bugs med I 
   */
   public PieceShape rotatedPiece(){
        boolean[][] rotatedShape = new boolean[getWidth()][getHeight()];

        for (int newRow = 0; newRow < this.piece.getWidth(); newRow++){            
            for (int newCol = 0; newCol < this.piece.getHeight(); newCol++){
                int gammalCol = getWidth() -newRow -1;
                int gammalRow = newCol;
                rotatedShape[newRow][newCol] = piece.getShape()[gammalRow][gammalCol];
            }
        }

        return new PieceShape(piece.getTile(), rotatedShape); 
   }

   //Tar den roterte brikken fra metoden over og setter den på brettet
   public PositionedPiece rotatedPositionedPieceCopy(){
       return new PositionedPiece(rotatedPiece(), coordinate);
   }
   
}
