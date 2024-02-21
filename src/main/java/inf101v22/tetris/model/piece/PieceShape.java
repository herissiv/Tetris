package inf101v22.tetris.model.piece;
import java.awt.Color;


import inf101v22.tetris.model.Tile;
public class PieceShape {

    private boolean[][] shape;
    private Tile tile;

    //Definerer brikkene T, S, Z, I, J, L, O
    // T
    public static boolean[][] T0 = new boolean[][] {
        {  true,  true,  true },
        { false,  true, false }
    }; 
    // S
    public static boolean[][] S0 = new boolean[][] {
        { false,  true,  true },
        {  true,  true, false }
    };
    // Z
    public static boolean[][] Z0 = new boolean[][] {
        {  true,  true, false },
        { false,  true,  true }
    };
    // I
    public static boolean[][] I0 = new boolean[][] {
        { true,  true,  true, true}

    };
    // J
    public static boolean[][] J0 = new boolean[][] {
        { true, false, false },
        { true, true,  true }
    };
    // L
    public static boolean[][] L0 = new boolean[][] {
        { false, false,  true },
        {  true,  true,  true }
    };
    // O
    public static boolean[][] O0 = new boolean[][] {
        {  true,  true},
        {  true,  true}
    };

    /*
    For å gi brikke distinkte farger definerer jeg dem slik, 
    kunne også bare skrevet formene X_i0 på enden, men det hadde blitt uoversiktelig
    */
    public static final PieceShape T = new PieceShape(new Tile(Color.GREEN,'T'),T0);
    public static final PieceShape S = new PieceShape(new Tile(Color.ORANGE,'S'),S0);
    public static final PieceShape Z = new PieceShape(new Tile(Color.RED,'Z'),Z0);
    public static final PieceShape O = new PieceShape(new Tile(Color.CYAN,'O'),O0);
    public static final PieceShape I = new PieceShape(new Tile(Color.BLUE,'I'),I0);
    public static final PieceShape L = new PieceShape(new Tile(Color.MAGENTA,'L'),L0);
    public static final PieceShape J = new PieceShape(new Tile(Color.YELLOW,'J'),J0);

    /*
    Definerer en liste med alle typer brikker i en liste, 
    slik at det er lett å ekstrahere tilfeldige brikker senere
    */
    public static final PieceShape[] STANDARD_TETRIS_PIECES = { T, S, Z, I, J, L, O };


    public PieceShape(Tile tile, boolean[][] shape){
        this.shape = shape;
        this.tile = tile;
    }

    //Returnerer selve fassongen til brikka 
    public boolean[][] getShape(){
        return this.shape;
    }

    public int getWidth(){
        return shape[0].length;
    }

    public int getHeight(){
        return shape.length;
    }

    //Returnerer en av flisene til brikka 
    public Tile getTile(){
        return this.tile;
    }

}
