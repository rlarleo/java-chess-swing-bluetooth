
import chess.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowChessGameSingle extends ChessBoard implements MouseListener, MouseMotionListener {

    private final int refreshRate = 5; //Amount of pixels moved before screen is refreshed
    private final ImageIcon[][] imgPlayer = new ImageIcon[2][6];
    private final String[] strPlayerName = {"", ""};
    private String strStatusMsg = "";
    private final CellMatrix cellMatrix = new CellMatrix();
    private int currentPlayer = 1, startRow = 7, startColumn = 7, pieceBeingDragged = 1;
    private final int startingX = 0;
    private final int startingY = 0;
    private int currentX = 0;
    private int currentY = 0;
    private int refreshCounter = 0;
    private boolean firstTime = true, hasWon = false, isDragging = false, kingSafe, drag = false;
    private final Pawn pawnObject = new Pawn();
    private final Rock rockObject = new Rock();
    private final Knight knightObject = new Knight();
    private final Bishop bishopObject = new Bishop();
    private final Queen queenObject = new Queen();
    private final King kingObject = new King();
    private int kingRow, kingCol;
    private int pplayer = 1, ppiece = 1;
    private int moveCount = 0;
    private final String[] moveRecord = new String[500];

    public WindowChessGameSingle() {
        super();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private String getPlayerMsg() {
        System.err.println("windowchessboard, getPlayerMSg() being running");
        if (hasWon) {
            return "Congrats " + strPlayerName[currentPlayer - 1] + ", you are the winner!";
        } else if (firstTime) {
            return "" + strPlayerName[0] + " you are white, " + strPlayerName[1] + " you are black. Press start to Play";
        } else {
            return "" + strPlayerName[currentPlayer - 1] + " move";
        }
    }

    //reset player matrix and pieces matrix
    private void resetBoard() {
        System.err.println("windowchessboard, resetboard() being running");
        hasWon = false;
        currentPlayer = 1;
        strStatusMsg = getPlayerMsg();
        cellMatrix.resetMatrix();
        repaint();
    }

    public void setupImages(ImageIcon[] imgWhite, ImageIcon[] imgBlack) {
        imgPlayer[0] = imgWhite;
        imgPlayer[1] = imgBlack;
        resetBoard();
    }

    public void setNames(String strPlayer1Name, String strPlayer2Name) {
        strPlayerName[0] = strPlayer1Name;
        strPlayerName[1] = strPlayer2Name;
        strStatusMsg = getPlayerMsg();
        repaint();
    }

    public String[] getMovementRecord() {
        return moveRecord;
    }

    protected void drawExtra(Graphics g) {
        System.err.println("windowchessboard, drawextra() being running");
        for (int i = 0; i < vecPaintInstructions.size(); i++) {
            System.err.println("vectorInstruction" + vecPaintInstructions.size());

            currentInstruction =  vecPaintInstructions.elementAt(i);
            int paintStartRow = currentInstruction.getStartRow();
            int paintStartColumn = currentInstruction.getStartColumn();
            int rowCells = currentInstruction.getRowCells();
            int columnCells = currentInstruction.getColumnCells();
            for (int row = 0; row < (paintStartRow + rowCells); row++) {
                for (int column = 0; column < (paintStartColumn + columnCells); column++) {
                    int playerCell = cellMatrix.getPlayerCell(row, column);
                    int pieceCell = cellMatrix.getPieceCell(row, column);

                    if (playerCell != 0) {
                        try {
                            g.drawImage((imgPlayer[playerCell - 1][pieceCell].getImage()), ((column + 1) * 50) - 45, ((row + 1) * 50) - 45, this);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }
                }
            }
        }

        if (isDragging) {
            g.drawImage((imgPlayer[currentPlayer - 1][pieceBeingDragged].getImage()), (currentX - 25), (currentY - 25), this);
        }
        g.setColor(new Color(51, 51, 51));
        g.fillRect(5, 405, 415, 25);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 13));
        g.drawString(strStatusMsg, 5, 425);

        vecPaintInstructions.clear(); //clear all paint instructions
    }

    public void newGame() {
        System.err.println("windowchessboard, newGame() being running");
        firstTime = false;
        resetBoard();
    }

    private void checkMove(int desRow, int desColumn) {
        System.err.println("windowchessboard, Checkmove() being running");
        boolean legalMove = false;

        //if moved onto own piece
        if (cellMatrix.getPlayerCell(desRow, desColumn) == currentPlayer) {
            strStatusMsg = "Can not move onto a piece that is yours";
        } else {
        	//find the move is valid or not
            switch (pieceBeingDragged) {
                case 0:
                    legalMove = pawnObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix(), currentPlayer);
                    break;
                case 1:
                    legalMove = rockObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 2:
                    legalMove = knightObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 3:
                    legalMove = bishopObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 4:
                    legalMove = queenObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 5:
                    legalMove = kingObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
            }
        }

        //if legal
        if (legalMove) {
            System.err.println("legalmove, inside check");
            int newDesRow = 7;
            int newDesColumn = 7;

            switch (pieceBeingDragged) {
                case 0:
                    newDesRow = pawnObject.getDesRow();
                    newDesColumn = pawnObject.getDesColumn();
                    break;
                case 1:
                    newDesRow = rockObject.getDesRow();
                    newDesColumn = rockObject.getDesColumn();
                    break;
                case 2:
                    newDesRow = knightObject.getDesRow();
                    newDesColumn = knightObject.getDesColumn();
                    break;
                case 3:
                    newDesRow = bishopObject.getDesRow();
                    newDesColumn = bishopObject.getDesColumn();
                    break;
                case 4:
                    newDesRow = queenObject.getDesRow();
                    newDesColumn = queenObject.getDesColumn();
                    break;
                case 5:
                    newDesRow = kingObject.getDesRow();
                    newDesColumn = kingObject.getDesColumn();
                    break;
            }

            pplayer = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
            ppiece = cellMatrix.getPieceCell(newDesRow, newDesColumn);

            cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
            cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);

            kingRow = cellMatrix.getKingRow(currentPlayer);
            kingCol = cellMatrix.getKingCol(currentPlayer);

            System.err.println("KingRow= " + kingRow + " KingCol= " + kingCol);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kingRow, kingCol);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                System.err.println("King is NOT Safe");
            }
            if (kingSafe) {
                cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
                if (cellMatrix.checkWinner(currentPlayer)) {
                    hasWon = true;
                    strStatusMsg = getPlayerMsg();
                } else { //turn change
                    if (currentPlayer == 1) {
                        moveRecord[moveCount++] = strPlayerName[currentPlayer - 1] + ": " + startRow + "," + startColumn + "->" + desRow + "," + desColumn;
                        currentPlayer = 2;
                    } else {
                        moveRecord[moveCount++] = strPlayerName[currentPlayer - 1] + ": " + startRow + "," + startColumn + "->" + desRow + "," + desColumn;
                        currentPlayer = 1;
                    }

                    strStatusMsg = getPlayerMsg();
                }
            } else {
                unsucessfullDrag();

                cellMatrix.setPlayerCell(newDesRow, newDesColumn, pplayer);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, ppiece);

                strStatusMsg = "" + strPlayerName[currentPlayer - 1] + ", Your King is checked";
                repaint();
            }
        } else {
            switch (pieceBeingDragged) {
                case 0:
                    strStatusMsg = pawnObject.getErrorMsg();
                    break;
                case 1:
                    strStatusMsg = rockObject.getErrorMsg();
                    break;
                case 2:
                    strStatusMsg = knightObject.getErrorMsg();
                    break;
                case 3:
                    strStatusMsg = bishopObject.getErrorMsg();
                    break;
                case 4:
                    strStatusMsg = queenObject.getErrorMsg();
                    break;
                case 5:
                    strStatusMsg = kingObject.getErrorMsg();
                    break;
            }
            unsucessfullDrag();
        }
    }

    private boolean isCheckMate(int curPlayer) {
        kingRow = cellMatrix.getKingRow(curPlayer);
        kingCol = cellMatrix.getKingCol(curPlayer);

        boolean checkRight = true, checkDownRightDiagonal = true, checkDown = true, checkDownLeftDiagonal = true, 
        		checkLeft = true, checkUpperLeftDiagonal = true, checkUp = true, checkUpperRightDiagonal = true;

        checkRight = checkCases(curPlayer, checkRight, kingRow+1, kingCol); // 말의 오른쪽

        checkDownRightDiagonal = checkCases(curPlayer, checkDownRightDiagonal, kingRow+1, kingCol-1); // 말의 우측 아래 대각선

        checkDown = checkCases(curPlayer, checkDown, kingRow, kingCol-1); // 말의 아래쪽

        checkDownLeftDiagonal = checkCases(curPlayer, checkDownLeftDiagonal, kingRow-1, kingCol-1); // 말의 좌측 아래 대각선

        checkLeft = checkCases(curPlayer, checkLeft, kingRow-1, kingCol); // 말의 왼쪽

        checkUpperLeftDiagonal = checkCases(curPlayer, checkUpperLeftDiagonal, kingRow-1, kingCol+1); // 말의 좌측 위 대각선

        checkUp = checkCases(curPlayer, checkUp, kingRow, kingCol+1); // 말의 위쪽

        checkUpperRightDiagonal = checkCases(curPlayer, checkUpperRightDiagonal, kingRow+1, kingCol+1); // 말의 우측 위 대각선

        if (checkRight && checkDownRightDiagonal && checkDown && checkDownLeftDiagonal && 
        		checkLeft && checkUpperLeftDiagonal && checkUp && checkUpperRightDiagonal) {
            return true;
        }
        return false;
    }

	private boolean checkCases(int curPlayer, boolean checkCase, int newKingRow, int newKingCol) {
		boolean legalMove;
		boolean safe;
		boolean isNewKingRow = newKingRow >= 0 && newKingRow <= 7;
		boolean isNewKingCol = newKingCol >= 0 && newKingCol <= 8;
		if (isNewKingRow  && isNewKingCol) {
            legalMove = kingObject.legalMove(kingRow, kingCol, newKingRow, newKingCol, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newKingRow, newKingCol);
            if (!legalMove || !safe) {
                checkCase = true;
            } else {
                checkCase = false;
            }
        }
		return checkCase;
	}

    private void unsucessfullDrag() {
        System.err.println("unsuccessfulDrag");
        cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
        cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);
    }

    private void updatePaintInstructions(int desRow, int desColumn) {
        System.err.println("updatePaintInstructions");
        currentInstruction = new PaintInstruction(startRow, startColumn, 1);
        vecPaintInstructions.addElement(currentInstruction);
        currentInstruction = new PaintInstruction(desRow, desColumn);
        vecPaintInstructions.addElement(currentInstruction);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseReleased(e);
    }

    public void mousePressed(MouseEvent e) {
        System.err.println("windowchessboard, mousepressed() being running");
        if (!hasWon && !firstTime) {
            int xTouchedLocation = e.getX();
            int yTouchedLocation = e.getY();

            final boolean isTouchInside = (xTouchedLocation > 5 && xTouchedLocation < 405) && (yTouchedLocation > 5 && yTouchedLocation < 405);
			if (isTouchInside) //in the correct bounds
            {
                //find startRow and StartColumn from where the player clicks on the board
                startRow = findWhichTileSelected(yTouchedLocation);
                startColumn = findWhichTileSelected(xTouchedLocation);
                System.err.println("START  " + startRow + " ," + startColumn);
                if (cellMatrix.getPlayerCell(startRow, startColumn) == currentPlayer) {

                    pieceBeingDragged = cellMatrix.getPieceCell(startRow, startColumn);
                    cellMatrix.setPieceCell(startRow, startColumn, 6);
                    cellMatrix.setPlayerCell(startRow, startColumn, 0);
                    isDragging = true;

                } else {
                    isDragging = false;
                    
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        System.err.println("mouseReleased");

        if (!drag) {
            cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
            cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);
        }

        if (isDragging) {
            isDragging = false;

            int desRow = findWhichTileSelected(currentY);
            int desColumn = findWhichTileSelected(currentX);
            checkMove(desRow, desColumn);

            kingRow = cellMatrix.getKingRow(currentPlayer);
            kingCol = cellMatrix.getKingCol(currentPlayer);

            System.err.println("KingRow= " + kingRow + " KingCol= " + kingCol);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kingRow, kingCol);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                strStatusMsg = "" + strPlayerName[currentPlayer - 1] + " move, Your King is checked";
                System.err.println("King is NOT Safe");
            }

            if (isCheckMate(currentPlayer)) {
                strStatusMsg = "CHECKMATE";
            }
            repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
        drag = true;
        if (isDragging) {
            int xTouchedLocation = e.getX();
            int yTouchedLocation = e.getY();

            final boolean isTouchInside = (xTouchedLocation > 5 && xTouchedLocation < 405) && (yTouchedLocation > 5 && yTouchedLocation < 405);
			if (isTouchInside) //in the correct bounds
            {
                if (refreshCounter >= refreshRate) {
                    currentX = xTouchedLocation;
                    currentY = yTouchedLocation;
                    refreshCounter = 0;
                    int desRow = findWhichTileSelected(currentY);
                    int desColumn = findWhichTileSelected(currentX);

                    updatePaintInstructions(desRow, desColumn);

                    repaint();

                } else {
                    refreshCounter++;
                }

            } else {
                isDragging = false;
                unsucessfullDrag();
                repaint();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

}
