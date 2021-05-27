

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
    private int currentX = 0;
    private int currentY = 0;
    private int refreshCounter = 0;
    private boolean firstTime = true, hasWon = false, isDragging = false, kingSafe, drag = false;
    private final PieceFactory pieceFactory = new PieceFactory();
    private int kingRow, kingCol;
    private int playerMousePosition = 1, piecePosition = 1;
    private int moveCount = 0;
    private final String[] moveRecord = new String[500];
    public static Color darkChacol = new Color(51, 51, 51); // add color
    public static Color White = new Color(255, 255, 255); // add color

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
        
        initialBoardPaint(g); // Initialized Board Painting Instruction

        if (isDragging) {
            g.drawImage((imgPlayer[currentPlayer - 1][pieceBeingDragged].getImage()), (currentX - 25), (currentY - 25), this);
        }
        
        boardDesign(g); // color, text style ... 

        vecPaintInstructions.clear(); //clear all paint instructions
    }

	private void boardDesign(Graphics g) {
		g.setColor(darkChacol);
        g.fillRect(5, 405, 415, 25);
		g.setColor(White);
        g.setFont(new Font("Arial", Font.PLAIN, 13));
        g.drawString(strStatusMsg, 5, 425);
	}

	private void initialBoardPaint(Graphics g) {
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
        final boolean isNotYourPiece = cellMatrix.getPlayerCell(desRow, desColumn) == currentPlayer;
		if (isNotYourPiece) {
            strStatusMsg = "Can not move onto a piece that is yours";
        } else {
        	//find the move is valid or not
            switch (pieceBeingDragged) {
                case 0:
                    legalMove = pieceFactory.pawn.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix(), currentPlayer);
                    break;
                case 1:
                    legalMove = pieceFactory.rock.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 2:
                    legalMove = pieceFactory.knight.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 3:
                    legalMove = pieceFactory.bishop.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 4:
                    legalMove = pieceFactory.queen.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 5:
                    legalMove = pieceFactory.king.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
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
                    newDesRow = pieceFactory.pawn.getDesRow();
                    newDesColumn = pieceFactory.pawn.getDesColumn();
                    break;
                case 1:
                    newDesRow = pieceFactory.rock.getDesRow();
                    newDesColumn = pieceFactory.rock.getDesColumn();
                    break;
                case 2:
                    newDesRow = pieceFactory.knight.getDesRow();
                    newDesColumn = pieceFactory.knight.getDesColumn();
                    break;
                case 3:
                    newDesRow = pieceFactory.bishop.getDesRow();
                    newDesColumn = pieceFactory.bishop.getDesColumn();
                    break;
                case 4:
                    newDesRow = pieceFactory.queen.getDesRow();
                    newDesColumn = pieceFactory.queen.getDesColumn();
                    break;
                case 5:
                    newDesRow = pieceFactory.king.getDesRow();
                    newDesColumn = pieceFactory.king.getDesColumn();
                    break;
            }
            
            // save current position
            saveCurrentPosition(newDesRow, newDesColumn);

            // set current move position
            setCurrentMovePosition(newDesRow, newDesColumn);

            // Where is King 
            getKingPosition();

            System.err.println("KingRow= " + kingRow + " KingCol= " + kingCol);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kingRow, kingCol);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                System.err.println("King is NOT Safe");
            }
            if (kingSafe) {
                setCurrentMovePosition(newDesRow, newDesColumn);
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

                cellMatrix.setPlayerCell(newDesRow, newDesColumn, playerMousePosition);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, piecePosition);

                strStatusMsg = "" + strPlayerName[currentPlayer - 1] + ", Your King is checked";
                repaint();
            }
        } else {
            switch (pieceBeingDragged) {
                case 0:
                    strStatusMsg = pieceFactory.pawn.getErrorMsg();
                    break;
                case 1:
                    strStatusMsg = pieceFactory.rock.getErrorMsg();
                    break;
                case 2:
                    strStatusMsg = pieceFactory.knight.getErrorMsg();
                    break;
                case 3:
                    strStatusMsg = pieceFactory.bishop.getErrorMsg();
                    break;
                case 4:
                    strStatusMsg = pieceFactory.queen.getErrorMsg();
                    break;
                case 5:
                    strStatusMsg = pieceFactory.king.getErrorMsg();
                    break;
            }
            unsucessfullDrag();
        }
    }

	private void getKingPosition() {
		kingRow = cellMatrix.getKingRow(currentPlayer);
		kingCol = cellMatrix.getKingCol(currentPlayer);
	}

	private void setCurrentMovePosition(int newDesRow, int newDesColumn) {
		cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
		cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
	}

	private void saveCurrentPosition(int newDesRow, int newDesColumn) {
		playerMousePosition = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
		piecePosition = cellMatrix.getPieceCell(newDesRow, newDesColumn);
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
		boolean isLegalMove;
		boolean isSafe;
		final boolean isNewKingPosition = newKingRow >= 0 && newKingRow <= 7  && newKingCol >= 0 && newKingCol <= 8;
		if (isNewKingPosition) {
            isLegalMove = pieceFactory.king.legalMove(kingRow, kingCol, newKingRow, newKingCol, cellMatrix.getPlayerMatrix());
            isSafe = cellMatrix.isKingSafe(curPlayer, newKingRow, newKingCol);
            if (!isLegalMove || !isSafe) {
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

            getKingPosition();

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
