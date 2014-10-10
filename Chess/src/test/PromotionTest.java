package test;

import system.Board;
import system.BoardManager;
import system.PlayManager;
import system.Position;
import unit.King;
import unit.Pawn;
import unit.Unit.Color;
import junit.framework.TestCase;

public class PromotionTest extends TestCase {
	
	BoardManager boardManager;
	PlayManager playManager;
	
	@Override
	protected void setUp() throws Exception {
		boardManager = new BoardManager();
		playManager = new PlayManager();
		Board.chessBoard.clear();
		Board.chessBoard.put(new Position(3, 0), new King(Color.WHITE));
		Board.chessBoard.put(new Position(3, 7), new King(Color.BLACK));
		Board.chessBoard.put(new Position(6, 1), new Pawn(Color.BLACK));
		boardManager.initPiecePos();

		super.setUp();
	}
	
	public void testPromotion() {
		playManager.playChess();
		Position position = new Position(6,0);
		assertEquals("\u265B", Board.chessBoard.get(position).unicode);
	}
}
