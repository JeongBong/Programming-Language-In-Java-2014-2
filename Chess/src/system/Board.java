package system;
// YG: Package 이름은 소문자로 시작해야함!

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import unit.Piece;

public class Board {
	// YG: 체스게임을 서버에서 돌리고, 여러 사람이 대전하는 상황이라고 가정해본다면-
	// YG: Board가 static이라면 하나밖에 존재할 수 없어서 서버 한 대당 한 게임밖에 못 돌아감.
	// YG: 서버에서 여러 개의 게임을 처리하게 한다고 가정하면
	// YG: Board에서 static을 제거하는게 더 맞는 쪽.
	
	public static Map<Position, Piece> chessBoard;
	// YG: Able은 보통 소문자로 붙여씀. attackable이 한 단어라서.
	public static Set<Position> attackAblePosSet = new HashSet<Position>();
	public static Set<Position> moveAblePosSet = new HashSet<Position>();

	static {
		chessBoard = new HashMap<Position, Piece>();
		BoardManager boardManager = new BoardManager();
		boardManager.createPiece();
		boardManager.initPiecePos();
	}
	
	// YG: Main함수만 들고 있는 Play라는 클래스를 만들기.
	public static void main(String args[]) {
		PlayManager gameManager = new PlayManager();
		gameManager.playChess();
	}

}