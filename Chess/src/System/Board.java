package system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import unit.Piece;

public class Board {

	public static Map<Position, Piece> chessBoard;
	public static Set<Position> attackAblePosSet = new HashSet<Position>();
	public static Set<Position> moveAblePosSet = new HashSet<Position>();

	static {
		chessBoard = new HashMap<Position, Piece>();
		BoardManager boardManager = new BoardManager();
		boardManager.createPiece();
		boardManager.initPiecePos();
	}

	public static void main(String args[]) {
		PlayManager gameManager = new PlayManager();
		gameManager.playChess();
	}

}