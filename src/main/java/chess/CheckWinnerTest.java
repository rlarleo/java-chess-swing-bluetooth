
package chess;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckWinnerTest {
	CellMatrix cellMatrix;
	

	@Before
	public void setup() {
		cellMatrix = new CellMatrix();
	}
	
	
	/**
	 * Purpose: player1 기준으로 checkWinner() 함수가 제대로 winner를 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), checkWinner()
	 * Expected: 1. currentPlayer == 1, checkPlayer == 2
	 *      		playerMatrix[5][5] = 1
	 *      		pieceMatrix[5][5] = king
	 *      		player2's king -> None
	 *              so, return true     		
	 *
	 * 			 2. currentPlayer == 1, checkPlayer == 2
	 * 				playerMatrix[5][5] = 1
	 *      		pieceMatrix[5][5] = king
	 *      		playerMatrix[3][5] = 2
	 *      		pieceMatrix[3][5] = king
	 *      		
	 *              so, return false
	 * 
	 */
	@Test
	public void player1CheckWinnerTest() {
		//test 1
		cellMatrix.setPlayerCell(5, 5, 1); // [5][5] 에 플레이어 1의 말
		cellMatrix.setPieceCell(5, 5, 5); // [5][5]에 플레이어 1의 king
		assertTrue(cellMatrix.checkWinner(1)); // player1이 winner인가? 현재 player2의 king이 없으니 true 예상
		
		//test 2
		cellMatrix.setPlayerCell(3, 5, 2); // 후에  [3][5]에 플레이어 2의 말 생성
		cellMatrix.setPieceCell(3,5,5); // [3][5]에 플레이어 2의 king
		assertFalse(cellMatrix.checkWinner(1)); // 이제 player2의 king도 생겼으니 플레이어 1이 winner가 아님. false 예상
		
	}
	/**
	 * Purpose: player2 기준으로 checkWinner() 함수가 제대로 winner를 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), checkWinner()
	 * Expected: 1. currentPlayer == 2, checkPlayer == 1
	 *      		playerMatrix[3][5] = 2
	 *      		pieceMatrix[3][5] = king
	 *      		player1's king -> None
	 *              so, return true     		
	 *
	 * 			 2. currentPlayer == 2, checkPlayer == 1
	 * 				playerMatrix[3][5] = 2
	 *      		pieceMatrix[3][5] = king
	 *      		playerMatrix[5][5] = 1
	 *      		pieceMatrix[5][5] = king
	 *      		
	 *              so, return false
	 * 
	 */
	@Test
	public void player2CheckWinnerTest() {
		cellMatrix.setPlayerCell(3, 5, 2); // [3][5]에 플레이어 2의 말
		cellMatrix.setPieceCell(3, 5, 5); // [3][5]에 플레이어 2의 king
		assertTrue(cellMatrix.checkWinner(2)); // player2가 winner인가? 현재 player1의 king이 없으니 true 예상
		
		cellMatrix.setPlayerCell(5, 5, 1); // 후에 [5][5]에 플레이어 1의 말 생성
		cellMatrix.setPieceCell(5,5,5); // [5][5]에 플레이어 1의 king
		assertFalse(cellMatrix.checkWinner(2)); //  이제 player1의 king도 생겼으니 player2가 winner가 아님. false 예상
		
	}
}
