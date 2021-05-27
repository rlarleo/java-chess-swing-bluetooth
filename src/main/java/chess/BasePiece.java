package chess;

/*********
 * class which contains a function checkMoveStraightDiagonal() which has shared (protected) variables
 * finalDesRow, finalDesColumn & strErrorMsg
 * this class is extended in all pieces classes-> [Piece].java
 */



abstract class BasePiece {

    protected int finalDesRow = 0;
    protected int finalDesColumn = 0;
    protected String strErrorMsg = "";
    public static final int moveEast = 0;
    public static final int moveWest = 1;
	public static final int moveNorth = 2;
	public static final int moveSouth = 3;
	public static final int moveNW = 4;
	public static final int moveNE = 5;
	public static final int moveSW = 6;
	public static final int moveSE = 7;
    //Method for checking the path to the destination and making sure nothing is in the way
	
	public int moveDir(int startRow, int startColumn, int desRow, int desColumn) {
		boolean moveOnColumn = startColumn != desColumn;
    	boolean notMoveOnColumn = startColumn == desColumn;
    	boolean moveOnRow = startRow != desRow;
    	boolean notMoveOnRow = startRow == desRow;
    	boolean north = desRow < startRow;
    	boolean south = !north;
    	boolean west = desColumn < startColumn;
    	boolean east = !west;
    	
		if(notMoveOnColumn&&moveOnRow&&north) return moveNorth;
		else if(notMoveOnColumn&&moveOnRow&&south) return moveSouth;
		else if(notMoveOnRow&&moveOnColumn&&west) return moveWest;
		else if(notMoveOnRow&&moveOnColumn&&east) return moveEast;
		else if(north&&west) return moveNW;
		else if(north&&east) return moveNE;
		else if(south&&west) return moveSW;
		else return moveSE;
	}
    protected boolean moveStraightAxis(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {
    	switch(moveDir(startRow,startColumn,desRow,desColumn)) {
    	case moveNorth:
    		//Checks each cell between the start row - 1 (since don't need to check the cell it is in) to the destination cell
            for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
                //Checks the cell is empty
                if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
                    return false;
                }
            } break;
    	case moveSouth:
    		for (int newRow = (startRow + 1); newRow < desRow; newRow++) {
    			if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
    				return false;
            }
        } break;
    	case moveWest:
    		for (int newColumn = (startColumn - 1); newColumn > desColumn; newColumn--) {			
				if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
					return false;
					}
				}break;
    	case moveEast:
    		for (int newColumn = (startColumn + 1); newColumn < desColumn; newColumn++) {
				if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
					return false;
					}
				}break;
		default: 
			strErrorMsg = ""; 
			return false;
    	}
    	
    	finalDesRow = desRow;
        finalDesColumn = desColumn;

        return true;
    }
    protected boolean moveDiagonal(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {
    	boolean legalNEdiagonal = (desRow - startRow) == (startColumn - desColumn);
    	boolean legalNWdiagonal = (desRow - startRow) == (desColumn - startColumn);
    	boolean legalSWdiagonal = (startRow - desRow) == (desColumn - startColumn);
    	boolean legalSEdiagonal = (startRow - desRow) == (startColumn - desColumn);
    	
    	strErrorMsg = "The destination is not on the same diagonal line"; //Default error message
        int newColumn;

        switch(moveDir(startRow,startColumn,desRow,desColumn)) {
        case moveNW:
        	if (legalNWdiagonal) {
                for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
                    newColumn = startColumn - (startRow - newRow);
                    if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                        return false;
                    }
                }
            } else {
                return false;
            }break;
        case moveNE:
        	if (legalNEdiagonal) {
		        for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
		            newColumn = startColumn + (startRow - newRow);
		            if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
		                return false;
		            }
		        }
		    } else {
		        return false;
		    }break;
        case moveSW:
        	if (legalSWdiagonal) {
		        for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

		            newColumn = startColumn - (newRow - startRow);

		            if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
		                return false;
		            }

		        }

		    } else {
		        return false;
		    }break;
        case moveSE:
        	if (legalSEdiagonal) {
		        for (int newRow = (startRow + 1); newRow < desRow; newRow++) {
		            newColumn = startColumn + (newRow - startRow);
		            if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
		                return false;
		            }
		        }
		    } else {
		        return false;
		    }break;
		default:
			strErrorMsg = "";
		    return false;
        }

        finalDesRow = desRow;
        finalDesColumn = desColumn;

        return true;
        
    }
   
    //Checks the cell to make sure it is empty

    private boolean checkAxisMove(int newRow, int newColumn, int[][] playerMatrix) {
        if (playerMatrix[newRow][newColumn] != 0) //If not empty
        {
            strErrorMsg = "A piece is blocking the route"; //Error message
            return false;
        }
        return true;
    }

    public int getDesRow() {
        return finalDesRow;
    }

    public int getDesColumn() {
        return finalDesColumn;
    }

    public String getErrorMsg() {
        return strErrorMsg;
    }
}
