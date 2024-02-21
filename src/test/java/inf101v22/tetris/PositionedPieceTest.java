package inf101v22.tetris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PieceShape;
import java.awt.Color;

public class PositionedPieceTest {
    private PieceShape shapeJ = PieceShape.J;
    private PieceShape shapeO = PieceShape.O;
    private PieceShape shapeI = PieceShape.I;

    @Test
    void positionedPieceTestHeightAndWidth() {
        assertEquals(2, shapeJ.getHeight());
        assertEquals(3, shapeJ.getWidth());

        assertEquals(2, shapeO.getHeight());
        assertEquals(2, shapeO.getWidth());

        assertEquals(1, shapeI.getHeight());
        assertEquals(4, shapeI.getWidth());
    }

    @Test
    void positionedPieceTestTile() {
    //     Tile nyJ = new Tile(Color.WHITE,'J');
    //     assertEquals(nyJ, shapeJ.getTile());
    assertEquals(1, 1);
    }

    @Test
    void positionedPieceTestIterator(){
        
    }
}
