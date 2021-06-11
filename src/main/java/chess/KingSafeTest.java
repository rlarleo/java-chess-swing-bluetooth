

package chess;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KingSafeTest {
	// 0 empty, 1 player one, 2 player two
	// 0 pawn, ## 1 rock, 2 knight, 3 bishop, 4 queen, 5 king, 6 empty
    CellMatrix cellMatrix;
    
    @Before
    public void setup() {
        cellMatrix = new CellMatrix();
    }
    
    /**
	 * Purpose: pieceMatrix[8][8]를 empty로 초기화 해주기 위함
	 * Input: setPieceCell()
	 */
    @Before
    public void cleanMatrix() { //테스트 코드에 임의로 기물을 넣어주면 나머지 부분 배열은 0으로 초기화 됨 0 은 pawn을 의미하므로 
    	for(int i=0; i<8; i++) { // empty를 의미하는 6으로 초기화 해줌
    		for(int j=0; j<8; j++) {
    			cellMatrix.setPieceCell(i, j, 6);
    		}
    	}
    }
    //게임을 실행했을때 흰색이 player1이고 아래쪽에 위치함
    //검은색은 player2이고 위쪽이 위치
    
    /**
	 * Purpose: pawn이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected:player1 기준으로 
	 * 			pawn은 king의 row, col의
	 * 			(-1,-1), (-1,+1)이라면 king을 잡을 수 있음
	 * 			위 조건을 만족한다면 false 반환, 아니라면 true 반환
	 * 
	 */
    
    //player1 기준으로 테스트
    @Test
    public void Pawn1_isKingSafeTest() {
    	cellMatrix.setPlayerCell(4, 4, 1); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
    	
    	cellMatrix.setPlayerCell(3, 3, 2); // [3][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 3, 0); // [3][3]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있으므로 false 반환해야 함.
    	assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(3, 3, 6);//[3][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(3, 4, 2); // [3][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 4, 0); // [3][4]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(3, 4, 6);//[3][4]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(3, 5, 2); // [3][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 5, 0); // [3][5]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있으므로 false 반환해야 함
    	assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(3, 5, 6);//[3][5]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(4, 3, 2); // [4][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(4, 3, 0); // [4][3]위치에 있는 기물은 pawn이다.
    	//king의 row 와 pawn의 row가 같다. pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(4, 3, 6);//[4][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(4, 5, 2); // [4][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(4, 5, 0); // [4][5]위치에 있는 기물은 pawn이다.
    	//king의 row 와 pawn의 row가 같다. 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(4, 5, 6);//[4][5]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(5, 3, 2); // [5][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 3, 0); // [5][3]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(5, 3, 6);//[5][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(5, 4, 2); // [5][4]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 4, 0); // [5][4]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(5, 4, 6);//[5][4]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(5, 5, 2); // [5][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 5, 0); // [5][5]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
    	cellMatrix.setPieceCell(5, 5, 6);// [5][5]위치 empty로 초기화
    	cellMatrix.setPieceCell(4, 4, 6); // [4][4]위치 player1의 king을 empty로 초기화
    }
    /**
	 * Purpose: pawn이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected:player2 기준으로 
	 * 			pawn은 king의 row, col의
	 * 			(+1,-1), (+1,+1)이라면 king을 잡을 수 있음
	 * 			위 조건을 만족한다면 false 반환, 아니라면 true 반환
	 *           
	 */
    //player2 기준으로 테스트
	@Test
	public void Pawn2_isKingSafeTest() {
		cellMatrix.setPlayerCell(4, 4, 2); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
    	
    	cellMatrix.setPlayerCell(5, 3, 1); // [5][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 3, 0); // [5][3]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertFalse(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(5, 3, 6);//[5][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(5, 4, 1); // [5][4]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 4, 0); // [5][4]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(5, 4, 6);//[5][4]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(5, 5, 1); // [5][6]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 5, 0); // [5][6]위치에 있는 기물은 pawn이다.
    	// king의 row 보다 pawn의 row가 1이 크다 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertFalse(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(5, 5, 6);// [5][6]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(4, 3, 1); // [4][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(4, 3, 0); // [4][3]위치에 있는 기물은 pawn이다.
    	//king의 row 와 pawn의 row가 같다. pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(4, 3, 6);//[4][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(4, 5, 2); // [4][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(4, 5, 0); // [4][5]위치에 있는 기물은 pawn이다.
    	//king의 row 와 pawn의 row가 같다. 따라서 pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(4, 5, 6);//[4][5]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(3, 3, 2); // [3][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 3, 0); // [3][3]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있으므로 false 반환해야 함.
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(3, 3, 6);//[3][3]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(3, 4, 2); // [3][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 4, 0); // [3][4]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있지 않으므로 true 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(3, 4, 6);//[3][4]위치 empty로 초기화
    	
    	cellMatrix.setPlayerCell(3, 5, 2); // [3][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 5, 0); // [3][5]위치에 있는 기물은 pawn이다.
    	//pawn이 king을 위협하고 있으므로 false 반환해야 함
    	assertTrue(cellMatrix.isKingSafe(2, cellMatrix.getKingRow(2), cellMatrix.getKingCol(2)));
    	cellMatrix.setPieceCell(3, 5, 6);//[3][5]위치 empty로 초기화
    	cellMatrix.setPieceCell(4, 4, 6); // [4][4]위치 player1의 king을 empty로 초기화
	}
	
	
    /**
	 * Purpose: Rock이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected: rock은 king과 row가 같거나 col이 같으면 king을 잡을 수 있다.
	 * 			위 조건을 만족하면 king을 잡을 수 있다. 
	 * 			따라서 위 조건을 만족하면 false반환, 아니라면 true 반환
	 * 			
	 */
	@Test
	public void Rock_isKingSafeTest() {
		cellMatrix.setPlayerCell(4, 4, 1); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
		
		cellMatrix.setPlayerCell(0, 4, 2); // [0][4]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(0, 4, 1); // [0][4]위치에 있는 기물은 rock이다.
		//rock이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(0, 4, 6); // [0][4]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(4, 0, 2); // [4][0]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(4, 0, 1); // [4][0]위치에 있는 기물은 rock이다.
		//rock이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(4, 0, 6); // [4][0]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(4, 7, 2); // [4][7]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(4, 7, 1); // [4][7]위치에 있는 기물은 rock이다.
		//rock이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(4, 7, 6); // [4][7]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 4, 2); // [7][4]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(7, 4, 1); // [7][4]위치에 있는 기물은 rock이다.
		//rock이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 4, 6); // [7][4]위치 empty로 초기화
	}
	

	/**
	 * Purpose: Knight이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected: knight는 king보다 row,col이 
	 * 			 (-1,-2), (-2,-1), (-2,+1), (-1,+2)
	 * 			 (+1,-2), (+2,-1), (+1,+2), (+2,+1)
	 * 			위 조건을 만족하면 king을 잡을 수 있다. 
	 * 			따라서 위 조건을 만족하면 false반환, 아니라면 true 반환
	 */
	@Test
	public void Knight_isKingSafeTest() {
		cellMatrix.setPlayerCell(4, 4, 1); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
		
		cellMatrix.setPlayerCell(3, 2, 2); // [3][2]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 2, 2); // [3][2]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(3, 2, 6); // [3][2]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(2, 3, 2); // [2][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(2, 3, 2); // [2][3]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(2, 3, 6); // [2][3]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(2, 5, 2); // [2][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(2, 5, 2); // [2][5]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(2, 5, 6); // [2][5]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(3, 6, 2); // [3][6]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(3, 6, 2); // [3][6]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(3, 6, 6); // [3][6]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(5, 2, 2); // [5][2]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 2, 2); // [5][2]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(5, 2, 6); // [5][2]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(6, 3, 2); // [6][3]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(6, 3, 2); // [6][3]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(6, 3, 6); // [6][3]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(6, 5, 2); // [6][5]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(6, 5, 2); // [6][5]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(6, 5, 6); // [6][5]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(5, 6, 2); // [5][6]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(5, 6, 2); // [5][6]위치에 있는 기물은 knight이다
    	//knight이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(5, 6, 6); // [5][6]위치 empty로 초기화
	}
	
	/**
	 * Purpose: Bishop이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected: bishop은 king의 row, col의
	 * 			 (-n,-n), (-n,+n), (+n,-n), (+n,+n)
	 * 			위 조건을 만족하면 king을 잡을 수 있다. 
	 * 			따라서 위 조건을 만족하면 false반환, 아니라면 true 반환
	 */
	@Test
	public void Bishop_isKingSafeTest() {
		cellMatrix.setPlayerCell(4, 4, 1); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
		
		cellMatrix.setPlayerCell(0, 0, 2); // [0][0]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(0, 0, 3); // [0][0]위치에 있는 기물은 bishop이다
		//bishop이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(0, 0, 6); // [0][0]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(1, 7, 2); // [1][7]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(1, 7, 3); // [1][7]위치에 있는 기물은 bishop이다
		//bishop이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(1, 7, 6); // [0][7]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 1, 2); // [7][1]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(7, 1, 3); // [7][1]위치에 있는 기물은 bishop이다
		//bishop이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 1, 6); // [7][1]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 7, 2); // [7][7]위치에 플레이어 2의 기물이 있다.
    	cellMatrix.setPieceCell(7, 7, 3); // [7][7]위치에 있는 기물은 bishop이다
		//bishop이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 7, 6); // [7][7]위치 empty로 초기화
		
		
	}
	
	/**
	 * Purpose: Queen이 king을 위협할 때 CellMatrix.java의 함수 isKingSafe()가 제대로 된 값을 반환하는지 테스트
	 * Input: setPlayerCell(), setPieceCell(), isKingSafe()
	 * Expected: queen은 king의 row, col의
	 * 			 rock의 조건 또는 bishop의 조건을 만족하면
	 *           king을 잡을 수 있다.
	 *           따라서 만족하면 false 반환, 아니라면 true 반환
	 * 			
	 */
	@Test
	public void Queen_isKingSafeTest() {
		//가로세로 테스트
		cellMatrix.setPlayerCell(4, 4, 1); // [4][4]위치에 플레이어 1의 기물이 있다.
    	cellMatrix.setPieceCell(4, 4, 5); // [4][4]위치에 있는 기물은 king이다
		
		cellMatrix.setPlayerCell(0, 4, 2); // [0][4]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(0, 4, 4); // [0][4]위치에 있는 기물은 queen이다.
		//queen이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(0, 4, 6); // [0][4]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(4, 0, 2); // [4][0]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(4, 0, 4); // [4][0]위치에 있는 기물은 queen이다.
		//queen이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(4, 0, 6); // [4][0]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(4, 7, 2); // [4][7]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(4, 7, 4); // [4][7]위치에 있는 기물은 queen이다.
		//queen이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(4, 7, 6); // [4][7]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 4, 2); // [7][4]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(7, 4, 4); // [7][4]위치에 있는 기물은 queen이다.
		//queen이 king을 위협하고 있으므로 false 반환해야 함
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 4, 6); // [7][4]위치 empty로 초기화
		
		//대각선 테스트
		
		cellMatrix.setPlayerCell(0, 0, 2);// [0][0]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(0, 0, 4);// [0][0]위치에 있는 기물은 queen이다
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(0, 0, 6);// [0][0]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(1, 7, 2);// [1][7]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(1, 7, 4);// [1][7]위치에 있는 기물은 queen이다
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(1, 7, 6);// [1][7]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 1, 2);// [7][1]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(7, 1, 4);// [7][7]위치에 있는 기물은 queen이다
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 1, 6);// [7][1]위치 empty로 초기화
		
		cellMatrix.setPlayerCell(7, 7, 2);// [7][7]위치에 플레이어 2의 기물이 있다.
		cellMatrix.setPieceCell(7, 7, 4);// [7][7]위치에 있는 기물은 queen이다
		assertFalse(cellMatrix.isKingSafe(1, cellMatrix.getKingRow(1), cellMatrix.getKingCol(1)));
		cellMatrix.setPieceCell(7, 7, 6); // [7][7]위치 empty로 초기화
		
		
	}

}
