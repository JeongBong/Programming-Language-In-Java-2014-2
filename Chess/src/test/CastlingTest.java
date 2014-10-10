package test;

import system.Board;
import system.BoardManager;
import system.PlayManager;
import system.Position;
import unit.King;
import unit.Rook;
import unit.Unit.Color;
import junit.framework.TestCase;

public class CastlingTest extends TestCase {

	BoardManager boardManager;
	PlayManager playManager;
	@Override
	protected void setUp() throws Exception {
		boardManager = new BoardManager();
		playManager = new PlayManager();
		Board.chessBoard.clear();
		Board.chessBoard.put(new Position(3, 0), new King(Color.WHITE));
		Board.chessBoard.put(new Position(3, 7), new King(Color.BLACK));
		Board.chessBoard.put(new Position(0, 7), new Rook(Color.BLACK));
		Board.chessBoard.put(new Position(7, 7), new Rook(Color.BLACK));
		boardManager.initPiecePos();

		super.setUp();
	}
	
	public void testCheckmate() {
		playManager.playChess();
		assertEquals(true, playManager.isCheckmate);
	}
}
