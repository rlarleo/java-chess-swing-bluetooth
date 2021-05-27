package chess;

public class PieceFactory extends BasePiece{
	
    public final Pawn pawn = new Pawn();
    public final Rock rock = new Rock();
    public final Knight knight = new Knight();
    public final Bishop bishop = new Bishop();
    public final Queen queen = new Queen();
    public final King king = new King();

}
