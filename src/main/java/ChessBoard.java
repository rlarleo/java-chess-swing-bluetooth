//chess board

import chess.PaintInstruction;

import java.awt.*;
import java.util.Vector;

public class ChessBoard extends Canvas {

    private final static Color white = new Color(255,255,255);
    private final static Color ocher = new Color(165, 130, 95);
    //private final static Color grey = new Color(51, 51, 51);
    protected PaintInstruction currentInstruction = null;
    protected final Vector<PaintInstruction> vecPaintInstructions = new Vector<>();
    private final static int numberOfTiles = 8;

    public void update(Graphics g) {
        System.err.println("chessboard, update() being running");
        paint(g);
    }

    public void paint(Graphics g) {
        System.err.println("chessboard, paint() being running");
        if (vecPaintInstructions.size() == 0) {

            currentInstruction = new PaintInstruction(0, 0, numberOfTiles);
            vecPaintInstructions.addElement(currentInstruction);

        }

        g.setColor(new Color(51, 51, 51));
        g.fillRect(50, 450, 450, 50); //Paint over the current status text

        for (int i = 0; i < vecPaintInstructions.size(); i++) {
            currentInstruction = vecPaintInstructions.elementAt(i);
            int startRow = currentInstruction.getStartRow();
            int startColumn = currentInstruction.getStartColumn();
            int rowCells = currentInstruction.getRowCells();
            int columnCells = currentInstruction.getColumnCells();
            //blank bgs
            for (int row = startRow; row < (startRow + rowCells); row++) {
                for (int column = startColumn; column < (startColumn + columnCells); column++) {
                    drawTile(row, column, g);
                }

            }

        }

        drawExtra(g);

    }
    
    private void drawTile(int row, int column, Graphics g) {
        //todo: add the cell labels A-H:1-8
    	//refactoring, Extract variable
    	boolean row_column_odd = ((row + 1) % 2 == 0)&&((column + 1) % 2) == 0;
    	boolean row_column_even = (row % 2 == 0)&&(column % 2 == 0);
        boolean row_odd_column_even = ((row + 1) % 2 == 0)&&(column % 2 == 0);
        boolean row_even_column_odd = (row % 2 == 0)&&((column + 1) % 2 == 0);
    			
        if(row_column_odd || row_column_even) 
        	g.setColor(white); //white
        if(row_odd_column_even || row_even_column_odd) 
            g.setColor(ocher); //ocher

        g.fillRect(coordinateToTile(column), coordinateToTile(row), 50, 50);

    }

    protected void drawExtra(Graphics g) //Any class extending the chess board can use this method to add extra things like player pieces
    {
    }
    

    protected int findWhichTileSelected(int coordinate) //Finds which tile the mouse is over
    {
        System.err.println("chessboard, findwhichtileselected() being running");
        for (int i = 0; i < numberOfTiles; i++) {
        	
            if (isSelectedTile(i,coordinate)) {
                return i;
            }

        }

        return -1;

    }
    
    private int coordinateToTile(int _coordinate) {
    	return 5 + (_coordinate * 50);
    }
    
    private boolean isSelectedTile(int i, int _coordinate) {
    	return (_coordinate >= (5 + (i * 50))) && (_coordinate <= (55 + (i * 50)));
    }
}
