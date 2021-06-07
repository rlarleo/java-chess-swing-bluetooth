package chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockMoveTest {
    Bishop bishop;
    Knight knight;
    Pawn pawn;
    Queen queen;
    Rock rock;
    int[][] playerMatrix;

    @Before
    public void setUp() {
        bishop = new Bishop();
        knight = new Knight();
        pawn = new Pawn();
        queen = new Queen();
        rock = new Rock();
        playerMatrix = new int[8][8];

    }
    /**
     * 1.
     * Purpose: Move Piece(Pawn) to valid or invalid place when blocked
     * Input: legalMove, check legal moving
     * Expected: pawn cannot move when blocked but if there are other pieces on the diagonal, pawn can moves.
     * 
     * 2.
     * Purpose: Move Piece(bishop) to valid or invalid place when blocked
     * Input: legalMove, check legal moving
     * Expected: bishop cannot moves when blocked
     *  
     * 3.
     * Purpose: Move Piece(knight) to valid or invalid place when blocked
     * Input: legalMove, check legal moving
     * Expected: knight can moves when blocked
     * 
     * 4.
     * Purpose: Move Piece(Queen) to valid or invalid place when blocked
     * Input: legalMove, check legal moving
     * Expected: Queen cannot moves when blocked
     * 
     * 5.
     * Purpose: Move Piece(Rock) to valid or invalid place when blocked
     * Input: legalMove, check legal moving
     * Expected:  Rock cannot move when blocked
     */
    @Test
    public void PawnBlockMoveTest() {
    	playerMatrix[4][4] = 1;
    	assertTrue(pawn.legalMove(5, 5, 4, 4, playerMatrix, 1));	// 대각선에 상대방 말이 있을 때
    	assertTrue(pawn.legalMove(3, 3, 4, 4, playerMatrix, 2));
    	
        assertFalse(pawn.legalMove(5, 4, 4, 3, playerMatrix, 1));
        assertFalse(pawn.legalMove(5, 2, 5, 3, playerMatrix, 1));
        assertFalse(pawn.legalMove(5, 4, 5, 3, playerMatrix, 1));
        
        playerMatrix[1][2] = 1;
        assertFalse(pawn.legalMove(3, 2, 1, 2, playerMatrix, 1));
        assertFalse(pawn.legalMove(3, 2, 4, 1, playerMatrix, 2));

        assertFalse(pawn.legalMove(2, 2, 1, 1, playerMatrix, 1));
        assertFalse(pawn.legalMove(3, 4, 5, 2, playerMatrix, 2));
    }
    
    public void BishopBlockMoveTest() {
        playerMatrix[1][1] = 1;
        assertFalse(bishop.legalMove(2, 0, 0, 2, playerMatrix));		// NE
        assertFalse(bishop.legalMove(2, 2, 0, 0, playerMatrix));		// NW
        assertFalse(bishop.legalMove(0, 0, 2, 2, playerMatrix));		// SE
        assertFalse(bishop.legalMove(0, 2, 2, 0, playerMatrix));		// SW
    }
    
    @Test
    public void KnightBlockMoveTest() {
    	playerMatrix[1][1] = 1;
    	playerMatrix[1][2] = 1;
    	playerMatrix[1][3] = 1;
    	playerMatrix[2][1] = 1;
    	playerMatrix[2][3] = 1;
    	playerMatrix[3][1] = 1;
    	playerMatrix[3][2] = 1;
    	playerMatrix[3][3] = 1;	
        assertFalse(knight.legalMove(2, 2, 2, 2, playerMatrix));		// stay
        assertTrue(knight.legalMove(2, 2, 0, 1, playerMatrix));			// 모든 방향 L 이동
        assertTrue(knight.legalMove(2, 2, 1, 0, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 0, 3, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 3, 0, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 1, 4, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 4, 1, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 3, 4, playerMatrix));
        assertTrue(knight.legalMove(2, 2, 4, 3, playerMatrix));
    }
    
    @Test
    public void QueenBlockMoveTest() {
        playerMatrix[1][1] = 1;
        playerMatrix[1][0] = 1;
        playerMatrix[0][1] = 1;
        assertFalse(queen.legalMove(0, 0, 2, 2, playerMatrix));		// 대각선
        assertFalse(queen.legalMove(0, 0, 2, 0, playerMatrix));		// 세로
        assertFalse(queen.legalMove(0, 0, 0, 2, playerMatrix));		// 가로
    }
    
    @Test
    public void RockBlockMoveTest() {
        playerMatrix[1][0] = 1;
        playerMatrix[0][1] = 1;
        assertFalse(rock.legalMove(0, 0, 2, 0, playerMatrix));		// 세로
        assertFalse(rock.legalMove(0, 0, 0, 2, playerMatrix));		// 가로
    }

}