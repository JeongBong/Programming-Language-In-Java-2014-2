package system;

import java.util.Iterator;

import unit.Bishop;
import unit.King;
import unit.Knight;
import unit.Pawn;
import unit.Queen;
import unit.Rook;
import unit.Unit.Color;

public class BoardManager {

	final static int MAX_BOARD_WIDTH =8; 
	final static int MAX_BOARD_HEIGHT =8; 
	
	void createPiece() {
		Board.chessBoard.put(new Position(3, 0), new King(Color.WHITE));
		Board.chessBoard.put(new Position(3, 7), new King(Color.BLACK));
		Board.chessBoard.put(new Position(4, 0), new Queen(Color.WHITE));
		Board.chessBoard.put(new Position(4, 7), new Queen(Color.BLACK));
		Board.chessBoard.put(new Position(7, 0), new Rook(Color.WHITE));
		Board.chessBoard.put(new Position(0, 0), new Rook(Color.WHITE));
		Board.chessBoard.put(new Position(7, 7), new Rook(Color.BLACK));
		Board.chessBoard.put(new Position(0, 7), new Rook(Color.BLACK));
		Board.chessBoard.put(new Position(5, 0), new Bishop(Color.WHITE));
		Board.chessBoard.put(new Position(2, 0), new Bishop(Color.WHITE));
		Board.chessBoard.put(new Position(5, 7), new Bishop(Color.BLACK));
		Board.chessBoard.put(new Position(2, 7), new Bishop(Color.BLACK));
		Board.chessBoard.put(new Position(6, 0), new Knight(Color.WHITE));
		Board.chessBoard.put(new Position(1, 0), new Knight(Color.WHITE));
		Board.chessBoard.put(new Position(6, 7), new Knight(Color.BLACK));
		Board.chessBoard.put(new Position(1, 7), new Knight(Color.BLACK));
		Board.chessBoard.put(new Position(0, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(1, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(2, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(3, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(4, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(5, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(6, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(7, 1), new Pawn(Color.WHITE));
		Board.chessBoard.put(new Position(0, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(1, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(2, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(3, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(4, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(5, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(6, 6), new Pawn(Color.BLACK));
		Board.chessBoard.put(new Position(7, 6), new Pawn(Color.BLACK));
	}

	// 모든 Piece가 보드에 놓인 후에 Piece들 간의 관계를 다시 고려해야 한다.
	public void initPiecePos() {
		Iterator<Position> pos = Board.chessBoard.keySet().iterator();

		while (pos.hasNext()) {
			Position pieceIter = (Position) pos.next();
			Board.chessBoard.get(pieceIter).resetMoveablePosList(pieceIter);
		}
	}

	void printBoard() {
		for (int yPos = 0; yPos < MAX_BOARD_HEIGHT; yPos++) {
			for (int xPos = 0; xPos < MAX_BOARD_WIDTH; xPos++) {
				Position boardIter = new Position(xPos, yPos);
				if (Board.chessBoard.containsKey(boardIter)) {
					System.out.print(Board.chessBoard.get(boardIter).unicode);
				} else {
					System.out.print("ㅁ");
				}
			}
			System.out.println();
		}
	}

}
