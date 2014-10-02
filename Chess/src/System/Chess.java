package System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Unit.*;
import Unit.Unit.Identity;

public class Chess {

	public static Map<Position, Piece> Board;
	public static Set<Position> attackAblePositionSet = new HashSet<Position>();

	static {
		Board = new HashMap<Position, Piece>();
		
		// YG: Piece의 생성자에서 Chess.Board.put하는건 좋지 않음.
		// 사용하지 않는 변수들이 이렇게나 많이 생기는 것도 좋지 않고.
		// YG: Chess의 start나 init 같은 메소드를 만들고
		// YG: 그 메소드를 실행시켜주면 Board를 리셋해주는 방식으로 고치기
		// YG: init() { Board.put(position, new King(posigion, Identity.WHITE_KING); ... }
				
		
		King W_King = new King(3, 0, Identity.WHITE_KING);
		King B_King = new King(3, 7, Identity.BLACK_KING);
		Queen W_Queen = new Queen(4, 0, Identity.WHITE_QUEEN);
		Queen B_Queen = new Queen(4, 7, Identity.BLACK_QUEEN);
		Rook WL_Rook = new Rook(7, 0, Identity.WHITE_ROOK);
		Rook WR_Rook = new Rook(0, 0, Identity.WHITE_ROOK);
		Rook BL_Rook = new Rook(7, 7, Identity.BLACK_ROOK);
		Rook BR_Rook = new Rook(0, 7, Identity.BLACK_ROOK);
		Bishop WL_Bishop = new Bishop(5, 0, Identity.WHITE_BISHOP);
		Bishop WR_Bishop = new Bishop(2, 0, Identity.WHITE_BISHOP);
		Bishop BL_Bishop = new Bishop(5, 7, Identity.BLACK_BISHOP);
		Bishop BR_Bishop = new Bishop(2, 7, Identity.BLACK_BISHOP);
		Knight WL_Knight = new Knight(6, 0, Identity.WHITE_KNIGHT);
		Knight WR_Knight = new Knight(1, 0, Identity.WHITE_KNIGHT);
		Knight BL_Knight = new Knight(6, 7, Identity.BLACK_KNIGHT);
		Knight BR_Knight = new Knight(1, 7, Identity.BLACK_KNIGHT);
		Pawn W0_Pawn = new Pawn(0, 1, Identity.WHITE_PAWN);
		Pawn W1_Pawn = new Pawn(1, 1, Identity.WHITE_PAWN);
		Pawn W2_Pawn = new Pawn(2, 1, Identity.WHITE_PAWN);
		Pawn W3_Pawn = new Pawn(3, 1, Identity.WHITE_PAWN);
		Pawn W4_Pawn = new Pawn(4, 1, Identity.WHITE_PAWN);
		Pawn W5_Pawn = new Pawn(5, 1, Identity.WHITE_PAWN);
		Pawn W6_Pawn = new Pawn(6, 1, Identity.WHITE_PAWN);
		Pawn W7_Pawn = new Pawn(7, 1, Identity.WHITE_PAWN);
		Pawn B0_Pawn = new Pawn(0, 6, Identity.BLACK_PAWN);
		Pawn B1_Pawn = new Pawn(1, 6, Identity.BLACK_PAWN);
		Pawn B2_Pawn = new Pawn(2, 6, Identity.BLACK_PAWN);
		Pawn B3_Pawn = new Pawn(3, 6, Identity.BLACK_PAWN);
		Pawn B4_Pawn = new Pawn(4, 6, Identity.BLACK_PAWN);
		Pawn B5_Pawn = new Pawn(5, 6, Identity.BLACK_PAWN);
		Pawn B6_Pawn = new Pawn(6, 6, Identity.BLACK_PAWN);
		Pawn B7_Pawn = new Pawn(7, 6, Identity.BLACK_PAWN);
		positionInitializer();
	}

	static void positionInitializer() {
		Iterator<Position> iterator = Board.keySet().iterator();

		while (iterator.hasNext()) {
			Position key = (Position) iterator.next();
			Board.get(key).resetPath();
		}
	}

	public static void main(String args[]) {
		Manager manager = new Manager();
		manager.playChess();
	}

}