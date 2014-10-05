package System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Unit.*;
import Unit.Unit.Color;

public class Board {

	public static Map<Position, Piece> chessBoard;
	public static Set<Position> attackAblePositionSet = new HashSet<Position>();

	static {
		chessBoard = new HashMap<Position, Piece>();
		
		// YG: Piece의 생성자에서 Chess.Board.put하는건 좋지 않음.
		// 사용하지 않는 변수들이 이렇게나 많이 생기는 것도 좋지 않고.
		// YG: Chess의 start나 init 같은 메소드를 만들고
		// YG: 그 메소드를 실행시켜주면 Board를 리셋해주는 방식으로 고치기
		// YG: init() { Board.put(position, new King(posigion, Identity.WHITE_KING); ... }
		// JB: 일단 init()으로 분리하기는 하였음. 
		
		createPiece();
		initPiecePosition();
	}

	
	static void createPiece(){
		chessBoard.put(new Position(3,0), new King(Color.WHITE));
		chessBoard.put(new Position(3,7), new King(Color.BLACK));
		chessBoard.put(new Position(4,0), new Queen(Color.WHITE));
		chessBoard.put(new Position(4,7), new Queen(Color.BLACK));
		chessBoard.put(new Position(7,0), new Rook(Color.WHITE));
		chessBoard.put(new Position(0,0), new Rook(Color.WHITE));
		chessBoard.put(new Position(7,7), new Rook(Color.BLACK));
		chessBoard.put(new Position(0,7), new Rook(Color.BLACK));
		chessBoard.put(new Position(5,0), new Bishop(Color.WHITE));
		chessBoard.put(new Position(2,0), new Bishop(Color.WHITE));
		chessBoard.put(new Position(5,7), new Bishop(Color.BLACK));
		chessBoard.put(new Position(2,7), new Bishop(Color.BLACK));
		chessBoard.put(new Position(6,0), new Knight(Color.WHITE));
		chessBoard.put(new Position(1,0), new Knight(Color.WHITE));
		chessBoard.put(new Position(6,7), new Knight(Color.BLACK));
		chessBoard.put(new Position(1,7), new Knight(Color.BLACK));
		chessBoard.put(new Position(0,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(1,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(2,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(3,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(4,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(5,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(6,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(7,1), new Pawn(Color.WHITE));
		chessBoard.put(new Position(0,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(1,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(2,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(3,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(4,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(5,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(6,6), new Pawn(Color.BLACK));
		chessBoard.put(new Position(7,6), new Pawn(Color.BLACK));		
	}
	
	static void initPiecePosition() {
		Iterator<Position> boardIter = chessBoard.keySet().iterator();

		while (boardIter.hasNext()) {
			Position key_position = (Position) boardIter.next();
			
			chessBoard.get(key_position).resetMoveablePositionList(key_position);
		}
	}

	public static void main(String args[]) {
		Manager manager = new Manager();
		manager.startChess();
	}

}