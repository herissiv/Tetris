package inf101v22.tetris.model;
import java.awt.Color;

public class Tile {

    public final Color farge;
    // Eneste bokstaven brukes til er å gjøre det lettere å lage tester som bekrefter at ting er som de skal 
    public final char bokstav;
    
    //Konstruktøren Tile bestemmer fargen til ruten i griddet, og vil ha en bokstav knyttet til den.
    public Tile(Color farge, char bokstav) {
        this.farge = farge; 
        this.bokstav = bokstav; 
    }
}
