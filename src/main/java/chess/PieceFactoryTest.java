
package chess;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PieceFactoryTest {
	PieceFactory pieceFactory;
	
	@Before
	public void setup() {
		pieceFactory = new PieceFactory();
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
}
