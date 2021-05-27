
import bluetooth.BtCommunication;
import chess.*;

import javax.microedition.io.StreamConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowChessGameBT extends ChessBoard implements MouseListener, MouseMotionListener {

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
    private final PieceFactory pieceFactory;
    private boolean isServer, myturn;
    private int kr, kc;
    private int pplayer = 1, ppiece = 1;
    private final BtCommunication btcomm;
    StreamConnection conn;
    Color darkCharcoal = new Color(51, 51, 51);
    Color White = new Color(255, 255, 255);

    private final String[] strRedPieces = {"whitePawn.png", "whiteRock.png", "whiteKnight.png", "whiteBishop.png", "whiteQueen.png", "whiteKing.png"};
    private final String[] strBluePieces = {"blackPawn.png", "blackRock.png", "blackKnight.png", "blackBishop.png", "blackQueen.png", "blackKing.png"};
    private final ImageIcon[] imgBlack = new ImageIcon[6];
    private final ImageIcon[] imgWhite = new ImageIcon[6];

    public WindowChessGameBT(boolean type)   {
        isServer = type;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        pieceFactory = new PieceFactory();
        
        btcomm = new BtCommunication();
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
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();
    }

    public void setupImages() {
        for (int i = 0; i < 6; i++) {
            imgWhite[i] = new ImageIcon(getClass().getResource("images/" + strRedPieces[i]));
            imgBlack[i] = new ImageIcon(getClass().getResource("images/" + strBluePieces[i]));
        }
        System.err.println(imgWhite[1]);
    }

    public void setNames(String strPlayer1Name, String strPlayer2Name) {

        strPlayerName[0] = strPlayer1Name;
        strPlayerName[1] = strPlayer2Name;
        strStatusMsg = getPlayerMsg();
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
		g.setColor(darkCharcoal);
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

    public void newGame(boolean isServer1, StreamConnection conn1) {
        isServer = isServer1;
        conn = conn1;
        System.err.println("*********Game Started **********");
        hasWon = false;
        if (isServer) {
            myturn = true;
            currentPlayer = 1;
            //strStatusGame = "Welcome, Your Turn First";
        } else {
            myturn = false;
            currentPlayer = 2;
            //strStatusGame = "Welcome, Server's Turn First";
        }

        cellMatrix.resetMatrix();
        //TODO:modify player name after reading info abt game
        setupImages();
        imgPlayer[1] = imgBlack;
        imgPlayer[0] = imgWhite;
        System.err.println("windowchessboard, newGame() being running");
        firstTime = false;
        resetBoard();
    }

    public void processCommands(String[] command) {

        for (String s : command) {
            System.err.println("Extracted" + s);
        }

        switch (command[0]) {
            case "makeEmpty":
                //make the source cell empty
                cellMatrix.setPieceCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), 6);
                cellMatrix.setPlayerCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), 0);
                break;
            case "playerCell":
                //update playerMatrix
                cellMatrix.setPlayerCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
                break;
            case "pieceCell":
                //update pieceMatrix
                changeTurn();
                cellMatrix.setPieceCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
                break;
        }
        repaint();
    }

    private void checkMove(int desRow, int desColumn) {
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();
        System.err.println("windowchessboard, Checkmove() being running");
        boolean legalMove = false;
        String msg;

        //if moved onto own piece
        boolean isNotYourPiece = cellMatrix.getPlayerCell(desRow, desColumn) == currentPlayer;
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
            System.err.println("legal move");
            msg = "makeEmpty#" + startRow + "#" + startColumn;
            btcomm.writeMessage(msg);
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

            pplayer = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
            ppiece = cellMatrix.getPieceCell(newDesRow, newDesColumn);

            System.err.println("pplayer " + pplayer + " ppiece" + ppiece);
            System.err.println("newDesRow " + newDesRow + " newDesColumn" + newDesRow);

            cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
            cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
            //int tmp=(currentPlayer%2)+1;
            msg = "playerCell#" + newDesRow + "#" + newDesColumn + "#" + currentPlayer;
            btcomm.writeMessage(msg);
            System.err.println("Sent: " + msg);

            kr = cellMatrix.getKingRow(currentPlayer);
            kc = cellMatrix.getKingCol(currentPlayer);

            System.err.println("KingRow= " + kr + " KingCol= " + kc);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kr, kc);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                System.err.println("King is NOT Safe");
            }

            if (kingSafe) {
                msg = "pieceCell#" + newDesRow + "#" + newDesColumn + "#" + pieceBeingDragged;
                btcomm.writeMessage(msg);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
                System.err.println("Sent: " + msg);
                if (cellMatrix.checkWinner(currentPlayer)) {
                    System.err.println("checking haswon??");
                    hasWon = true;
                    strStatusMsg = getPlayerMsg();
                } else {
                    changeTurn();
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
            System.err.println("ILLEGAL");

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
        if (myturn) {
            System.err.println("windowchessboard, mousepressed() being running");
            if (!hasWon && !firstTime) {
                int xTouchedLocation = e.getX();
                int yTouchedLocation = e.getY();

				boolean isTouchedInside = (xTouchedLocation > 5 && xTouchedLocation < 405) && (yTouchedLocation > 5 && yTouchedLocation < 405);
				if (isTouchedInside) //in the correct bounds
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
    }

    public void mouseReleased(MouseEvent e) {
        if (myturn) {
            if (!drag) {
                cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
                cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);
            }
            if (isDragging) {
                isDragging = false;
                int desRow = findWhichTileSelected(currentY);
                int desColumn = findWhichTileSelected(currentX);
                checkMove(desRow, desColumn);

                kr = cellMatrix.getKingRow(currentPlayer);
                kc = cellMatrix.getKingCol(currentPlayer);

                System.err.println("KingRow= " + kr + " KingCol= " + kc);
                kingSafe = cellMatrix.isKingSafe(currentPlayer, kr, kc);

                if (kingSafe) {
                    System.err.println("King is Safe");
                } else {
                    strStatusMsg = "" + strPlayerName[currentPlayer - 1] + " move, Your King is checked";
                    System.err.println("King is NOT Safe");
                }
                repaint();
            }
        }
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();
    }

    public void mouseDragged(MouseEvent e) {
        if (myturn) {
            drag = true;
            if (isDragging) {
                int xTouchedLocation = e.getX();
                int yTouchedLocation = e.getY();

				final boolean isTouchInside = xTouchedLocation > 5 && xTouchedLocation < 405 && yTouchedLocation > 5 && yTouchedLocation < 405;
				if (isTouchInside) //in the correct bounds
                {
                    if (refreshCounter >= refreshRate) {
                        currentX = xTouchedLocation;
                        currentY = yTouchedLocation;
                        refreshCounter = 0;
                        int desRow = findWhichTileSelected(currentY);
                        int desColumn = findWhichTileSelected(currentX);

                        updatePaintInstructions(desRow, desColumn);
                        System.err.println("desRow: " + desRow + "desColumn" + desColumn);
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
    }

    public void changeTurn() {
        //change turn
        myturn = myturn == false ? true : false;
        if (isServer && myturn) {
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }

    }

    public void mouseMoved(MouseEvent e) {
    }

}
