

package chess;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Purpose: CellMatrix.java의 get 함수들이 제대로 실행되는지 테스트
 * Input: setPlayerCell(), setPieceCell(), getKingRow(), getKingCol, getPlayerCell(), getPieceCell()
 * 
 * 	
 */
public class CellMatrixgetFuncTest {
	CellMatrix cellMatrix;
	
	@Before
	public void setup() {
		cellMatrix = new CellMatrix();
	}
	
	/**
	 * Purpose: getKingRow함수가 제대로 king의 row값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), getKingRow() 
	 * Expected: 1. return 7 -> equal
	 * 			 2. return 4 -> equal
	 */
	@Test
	public void getKingRowTest() {
		//test 1
		cellMatrix.setPlayerCell(7, 5, 1); // [7][5]에 플레이어 1의 말 생성
		cellMatrix.setPieceCell(7, 5, 5); // [7][5]에 플레이어 1의 king 생성
		assertEquals(cellMatrix.getKingRow(1),7); // 플레이어 1의 king의 row는 7이므로 7과 비교
		
		//test 2
		cellMatrix.setPlayerCell(4, 3, 2); // [4][3]에 플레이어 2의 말 생성
		cellMatrix.setPieceCell(4, 3, 5); // [4][3]에 플레이어 2의 king 생성
		assertEquals(cellMatrix.getKingRow(2),4); // 플레이어 2의 king의 row는 4이므로 4와 비교
	}
	/**
	 * Purpose: getKingCol함수가 제대로 king의 col값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), getKingRow()
	 * Expected: 1. return 5 -> equal
	 * 			 2. return 3 -> equal
	 */
	@Test
	public void getKingColTest() {
		//test 1
		cellMatrix.setPlayerCell(7, 5, 1); //[7][5]에 플레이어 1의 말 생성
		cellMatrix.setPieceCell(7, 5, 5); // [7][5]에 플레이어 1의 king 생성
		assertEquals(cellMatrix.getKingCol(1),5); // 플레이어 1의 king의 col은 5이므로 5와 비교
		
		//test2
		cellMatrix.setPlayerCell(4, 3, 2); // [4][3]에 플레이어 2의 말 생성
		cellMatrix.setPieceCell(4, 3, 5); // [4][3]에 플레이어 2의 king 생성
		assertEquals(cellMatrix.getKingCol(2),3); // 플레이어 2의 king의 col은 3이므로 3과 비교
	}
	/**
	 * Purpose: getPlayerCell() 함수가 해당 위치의 플레이어가 누군지 정확하게 반환하는지 테스트
	 * Input: setPlayerCell(), getPlayerCell()
	 * Expected: 1. return 1 -> equal
	 * 			 2. return 2 -> equal
	 */
	@Test
	public void getPlayerCellTest() {
		//test 1
		cellMatrix.setPlayerCell(0, 0, 1); // [0][0]에 플레이어 1의 말 생성
		assertEquals(cellMatrix.getPlayerCell(0, 0), 1); // getPlayerCell함수는 해당 위치의 말의 플레이어를 반환하는 함수
														 // 따라서 [0][0]의 말의 플레이어를 1과 비교
		//test 2
		cellMatrix.setPlayerCell(1, 1, 2); // [1][1]에 플레이어 2의 말 생성
		assertEquals(cellMatrix.getPlayerCell(1, 1), 2); // [1][1]의 말의 플레이어를 2와 비교
		
	}
	/**
	 * Purpose: getPieceCell() 함수가 해당 위치의 기물이 어떤것인지 정확하게 반환하는지 테스트
	 * Input: setPlayerCell(), getPlayerCell()
	 * Expected: 1. return 1 -> equal 
	 * 			 2. return 2 -> equal
	 * 			 3. return 3 -> equal
	 * 			 4. return 4 -> equal
	 *			 5. return 5 -> equal
	 *			 6. return 6 -> equal
	 */
	@Test
	public void getPieceCellTest() {
		//test 1
		cellMatrix.setPieceCell(0, 0, 0); // [0][0]에 pawn을 생성 (0==pawn)
		assertEquals(cellMatrix.getPieceCell(0, 0),0); // getPieceCell함수는 해당 위치의 말이 어떤말인지 반환하는 함수
													   // 따라서 [0][0]의 말이 pawn(==0)이 맞는지 비교
		//test 2
		cellMatrix.setPieceCell(1, 1, 1); // [1][1]에 rock을 생성 (1==rock)
		assertEquals(cellMatrix.getPieceCell(1, 1),1); // [1][1]의 말이 rock(==1)이 맞는지 비교
		
		//test 3
		cellMatrix.setPieceCell(2, 2, 2); // [2][2]에 knight을 생성 (2==knight)
		assertEquals(cellMatrix.getPieceCell(2, 2),2); // [2][2]의 말이 knight(==2)이 맞는지 비교

		//test 4
		cellMatrix.setPieceCell(3, 3, 3); // [3][3]에 bishop을 생성 (3==bishop)
		assertEquals(cellMatrix.getPieceCell(3, 3),3); // [3][3]의 말이 bishop(==3)이 맞는지 비교
		
		//test 5
		cellMatrix.setPieceCell(4, 4, 4); // [4][4]에 queen을 생성 (4==queen)
		assertEquals(cellMatrix.getPieceCell(4, 4),4); // [4][4]의 말이 queen(==4)이 맞는지 비교
		
		//test 6
		cellMatrix.setPieceCell(5, 5, 5); // [5][5]에 king을 생성 (5==king)
		assertEquals(cellMatrix.getPieceCell(5, 5),5); // [5][5]의 말이 king(==5)이 맞는지 비교
		
	}
	

}
