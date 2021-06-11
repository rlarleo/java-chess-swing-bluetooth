package chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AllTest {
    Bishop bishop;
    King king;
    Knight knight;
    Pawn pawn;
    Queen queen;
    Rock rock;
    int[][] playerMatrix;
	PieceFactory pieceFactory;
    CellMatrix cellMatrix;

    @Before
    public void setUp() {
        pawn = new Pawn();
        bishop = new Bishop();
        king = new King();
        knight = new Knight();
        queen = new Queen();
        rock = new Rock();
        playerMatrix = new int[8][8];
		pieceFactory = new PieceFactory();
		cellMatrix = new CellMatrix();


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
    @Test
    public void PawnBlockMoveTest() {
    	playerMatrix[4][4] = 1;
    	assertTrue(pawn.legalMove(5, 5, 4, 4, playerMatrix, 1));	// 대각선에 상대방 말이 있을 때
    	assertTrue(pawn.legalMove(3, 3, 4, 4, playerMatrix, 2));
    	
        assertFalse(pawn.legalMove(5, 4, 4, 3, playerMatrix, 1));
        assertFalse(pawn.legalMove(5, 2, 5, 3, playerMatrix, 1));
        assertFalse(pawn.legalMove(5, 4, 5, 3, playerMatrix, 1));
        assertFalse(pawn.legalMove(5, 4, 5, 4, playerMatrix, 2));
        
        playerMatrix[1][2] = 1;
        
        assertFalse(pawn.legalMove(3, 2, 1, 2, playerMatrix, 1));
        assertFalse(pawn.legalMove(2, 2, 1, 2, playerMatrix, 1));

        assertFalse(pawn.legalMove(3, 2, 4, 1, playerMatrix, 2));

        assertFalse(pawn.legalMove(2, 2, 1, 1, playerMatrix, 1));
        assertFalse(pawn.legalMove(3, 4, 5, 2, playerMatrix, 2));
    }
    
    @Test
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
	/**
	 * Purpose: PieceFactory가 Pawn을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), Pawn()
	 * Expected: pawn이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void PawnObjectTest() {
		assertNotNull(pieceFactory.pawn);
	}
	
	/**
	 * Purpose: PieceFactory가 Rock을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), Rock()
	 * Expected: rock이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void RockObjectTest() {
		assertNotNull(pieceFactory.rock);
	}
	
	/**
	 * Purpose: PieceFactory가 Knight을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), Knight()
	 * Expected: knight이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void KnightObjectTest() {
		assertNotNull(pieceFactory.knight);
	}
	
	/**
	 * Purpose: PieceFactory가 Bishop을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), Bishop()
	 * Expected: bishop이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void BishopObjectTest() {
		assertNotNull(pieceFactory.bishop);
	}
	
	/**
	 * Purpose: PieceFactory가 Queen을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), Queen()
	 * Expected: queen이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void QueenObjectTest() {
		assertNotNull(pieceFactory.queen);
	}
	
	/**
	 * Purpose: PieceFactory가 King을 제대로 생성하는지 테스트
	 * Input: PieceFactory(), King()
	 * Expected: king이 생성돼서 NotNull 이어야 함	
	 */
	@Test
	public void KingObjectTest() {
		assertNotNull(pieceFactory.king);
	}
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