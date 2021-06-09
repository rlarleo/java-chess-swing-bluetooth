package chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BasicMoveTest {
    Bishop bishop;
    King king;
    Knight knight;
    Pawn pawn;
    Queen queen;
    Rock rock;
    int[][] playerMatrix;

    @Before
    public void setUp() {
        pawn = new Pawn();
        bishop = new Bishop();
        king = new King();
        knight = new Knight();
        queen = new Queen();
        rock = new Rock();
        playerMatrix = new int[8][8];

    }
    /**
     * 1.
     * Purpose: Move Piece(Pawn) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected: pawn can move 2 spaces at starting point, only moves vertically etc..
     * 
     * 2.
     * Purpose: Move Piece(bishop) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected: bishop only moves diagonally
     *  
     * 3.     
     * Purpose: Move Piece(king) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected: king only moves 1 spaces
     * 
     * 4.
     * Purpose: Move Piece(knight) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected: knight only moves L shape
     * 
     * 5.
     * Purpose: Move Piece(Queen) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected: Queen can move horizontally, vertically, diagonally
     * 
     * 6.
     * Purpose: Move Piece(Rock) to valid or invalid place
     * Input: legalMove, check legal moving
     * Expected:  Rock can moves horizontally, vertically
     */
    
    @Test
    public void PawnBasicMoveTest() {
        assertFalse(pawn.legalMove(0, 0, 0, 0, playerMatrix, 1));	//stay
        
    	assertTrue(pawn.legalMove(2, 2, 3, 2, playerMatrix, 2));	// down 1
    	assertTrue(pawn.legalMove(2, 2, 1, 2, playerMatrix, 1));	// up 1

    	assertTrue(pawn.legalMove(1, 1, 3, 1, playerMatrix, 2));	// starting point down 2
    	assertTrue(pawn.legalMove(6, 1, 4, 1, playerMatrix, 1));	// starting point up 2
    	
    	assertFalse(pawn.legalMove(6, 1, 3, 1, playerMatrix, 1));	// starting point up 3

    	assertFalse(pawn.legalMove(2, 2, 3, 2, playerMatrix, 1));	// Reverse up, down
    	assertFalse(pawn.legalMove(2, 2, 1, 2, playerMatrix, 2));

    	assertFalse(pawn.legalMove(2, 2, 2, 3, playerMatrix, 1));	// Vertical
    	assertFalse(pawn.legalMove(2, 3, 0, 4, playerMatrix, 1));	// L
    	
    	assertFalse(pawn.legalMove(2, 2, 3, 3, playerMatrix, 1));	// 대각선에 비었을 때
    	assertFalse(pawn.legalMove(2, 2, 3, 3, playerMatrix, 2));    	
    }
    
    @Test
    public void BishopBasicMoveTest() {
        assertTrue(bishop.legalMove(0, 0, 2, 2, playerMatrix));		// 대각선
        assertFalse(bishop.legalMove(0, 0, 2, 0, playerMatrix));		// 세로
        assertFalse(bishop.legalMove(0, 0, 0, 2, playerMatrix));		// 가로
        assertFalse(bishop.legalMove(0, 0, 3, 1, playerMatrix));		// L
        assertFalse(bishop.legalMove(0, 0, 0, 0, playerMatrix));		// stay
    }

    @Test
    public void KingBasicMoveTest() {
        assertTrue(king.legalMove(1, 1, 0, 0, playerMatrix));		// 모든 방향 1칸 이동
        assertTrue(king.legalMove(1, 1, 0, 1, playerMatrix));
        assertTrue(king.legalMove(1, 1, 1, 0, playerMatrix));
        assertTrue(king.legalMove(1, 1, 2, 0, playerMatrix));
        assertTrue(king.legalMove(1, 1, 0, 2, playerMatrix));
        assertTrue(king.legalMove(1, 1, 2, 1, playerMatrix));
        assertTrue(king.legalMove(1, 1, 1, 2, playerMatrix));
        assertTrue(king.legalMove(1, 1, 2, 2, playerMatrix));
        
        assertFalse(king.legalMove(1, 1, 1, 1, playerMatrix));		// stay
        assertFalse(king.legalMove(1, 1, 3, 1, playerMatrix));		// 2칸 이동
    }

    @Test
    public void KnightBasicMoveTest() {
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
    public void QueenBasicMoveTest() {
        assertTrue(queen.legalMove(2, 2, 2, 0, playerMatrix));		// N
        assertTrue(queen.legalMove(2, 2, 4, 2, playerMatrix));		// S
        assertTrue(queen.legalMove(2, 2, 0, 2, playerMatrix));		// E
        assertTrue(queen.legalMove(2, 2, 2, 4, playerMatrix));		// W
        assertTrue(queen.legalMove(2, 2, 0, 0, playerMatrix));		// NE
        assertTrue(queen.legalMove(2, 2, 4, 0, playerMatrix));		// NW
        assertTrue(queen.legalMove(2, 2, 0, 4, playerMatrix));		// SE
        assertTrue(queen.legalMove(2, 2, 4, 4, playerMatrix));		// SW

        assertFalse(queen.legalMove(0, 0, 3, 1, playerMatrix));		// L
        assertFalse(queen.legalMove(0, 0, 0, 0, playerMatrix));		// stay
    }

    @Test
    public void RockBasicMoveTest() {
        assertTrue(rock.legalMove(0, 0, 2, 0, playerMatrix));		// S
        assertTrue(rock.legalMove(0, 0, 0, 2, playerMatrix));		// N
        assertTrue(rock.legalMove(2, 0, 0, 0, playerMatrix));		// E
        assertTrue(rock.legalMove(0, 2, 0, 0, playerMatrix));		// W 
        
    	assertFalse(rock.legalMove(0, 0, 2, 2, playerMatrix));		// 대각선
        assertFalse(rock.legalMove(0, 0, 3, 1, playerMatrix));		// L
        assertFalse(rock.legalMove(0, 0, 0, 0, playerMatrix));		// stay
    }
    
}