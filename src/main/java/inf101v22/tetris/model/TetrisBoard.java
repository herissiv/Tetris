package inf101v22.tetris.model;
import inf101v22.grid.Coordinate;

import inf101v22.grid.Grid;



public class TetrisBoard extends Grid<Tile> {

    private int rows;
    private int cols;
    public final Tile tile;
    public int forkastaRader;

    //Definerer brettet med en type tile 
    public TetrisBoard(int rows, int cols, Tile tile){
        super(rows,cols, tile);
        this.rows = rows; 
        this.cols = cols;
        this.tile = tile;
    }
    //Setter tile-en til å være en default svart rute 
    public TetrisBoard(int rows,int cols){
        this(rows, cols, null);
    }

    public char[][] toCharArray2d(){
        char[][] boksliste = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tile == null) {
                    boksliste[i][j] = '-';
                }
                else{
                    boksliste[i][j] = this.get(new Coordinate(i, j)).bokstav;
                }
            }
        }
        return boksliste;
    }

    /**
     *Hjepemetode som sier om en rad er tom (en full rad som skal bli tom)
     * @param row
     * @return
     */
    private boolean isRowEmpty(int row){
        for (int col = 0; col < cols; col++){
            if (this.get(new Coordinate(row, col))==null){
                return false;
            }
        }
        return true;
    }

    //Hjelpemetode som fjerner alle ruter
    private void fillEmptyRow(int row){
        for (int col = 0; col<cols; col++){
            this.set(new Coordinate(row, col), null);
        }
    }

    /*
    Kopierer neste ufylte rad for å så erstatte den 
    tomme raden med den kopierte raden  
    */
    private void copyNonEmptyRows(int targetRow,int sourceRow){
        
        for (int col = 0; col<cols; col++){
            Tile newTile = this.get(new Coordinate(targetRow, col));
            set(new Coordinate(sourceRow,col),newTile);
        }
    }

    /**
     * Tar ibruk de 3 hjelpemetodene ovenfor for å fjerne fulle ruter, 
     * og flytte ned resten 
     * PS: Denne funker ikke som planlagt 
     * @return Antall ruter som har blitt fjerna 
     */
    public int explodeRows(){
        int rowMinus1A = rows-1;
        int rowMinus1B = rows-1;
        forkastaRader = 0;
        while(rowMinus1A>=0){
          
            while(rowMinus1B>=0 && isRowEmpty(rowMinus1B)){
                forkastaRader++;
                rowMinus1B--;
            }
        
            if (rowMinus1B>=0){
                copyNonEmptyRows(rowMinus1B,rowMinus1A);

            }
            else{
                fillEmptyRow(rowMinus1A);
            }
            rowMinus1A--;
            rowMinus1B--;
            
        }
        return this.forkastaRader;
    }
  
}
