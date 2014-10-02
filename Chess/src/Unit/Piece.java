package Unit;

import java.util.ArrayList;
import java.util.Iterator;

import System.*;


abstract public class Piece extends Unit{
	final static int maxMoveSize =7;
	
	// YG: pieceColor라는게 의미가 다른듯. color가 id에 다 담겨있으니까...
	// YG: movedirection? 같은 의미를 담도록 바꿔보는건?
	int pieceColor;
	int moveCount;
	Position position;
	public String unicode;
	public ArrayList<Position> moveAblePosition = new ArrayList<Position>();
	public ArrayList<Position> attackAblePosition = new ArrayList<Position>();

	Piece(int xPosition, int yPosition, Identity id) {
		Position position = new Position(xPosition, yPosition);
		this.position = position;

		this.id = id;
		this.moveCount = 0;
		 // YG: 클래스마다 달라지는 내용은 클래스마다 따로 넣어두는게 좋음.
		 // YG: 클래스 내부에 모양도 있고, 생성되는 위치를 알면 색상도 알 수 있지 않음?
		if (this.id == Identity.WHITE_KING)
			this.unicode = "\u2654";
		if (this.id == Identity.WHITE_QUEEN)
			this.unicode = "\u2655";
		if (this.id == Identity.WHITE_ROOK)
			this.unicode = "\u2656";
		if (this.id == Identity.WHITE_BISHOP)
			this.unicode = "\u2657";
		if (this.id == Identity.WHITE_KNIGHT)
			this.unicode = "\u2658";
		if (this.id == Identity.WHITE_PAWN)
			this.unicode = "\u2659";
		if (this.id == Identity.BLACK_KING)
			this.unicode = "\u265A";
		if (this.id == Identity.BLACK_QUEEN)
			this.unicode = "\u265B";
		if (this.id == Identity.BLACK_ROOK)
			this.unicode = "\u265C";
		if (this.id == Identity.BLACK_BISHOP)
			this.unicode = "\u265D";
		if (this.id == Identity.BLACK_KNIGHT)
			this.unicode = "\u265E";
		if (this.id == Identity.BLACK_PAWN)
			this.unicode = "\u265F";

		Chess.Board.put(position, this);
	}

	// YG: Board 클래스를 따로 만들게 된다면 move 함수도 Board 클래스로 옮기는게 적절할 듯. move(Piece selected);
	public void move() {
		Position position = inputPosition();
		resetPath();

		while (!moveAblePosition.contains(position)) {
			System.out.println(String
					.format("접근할 수 없습니다. PIECE Id: %d, 현재 위치 x: %d, y: %d, 이동하려는 위치 x: %d, y: %d",
							this.id.getNo(), this.position.getxPos(), this.position.getyPos(),
							position.getxPos(), position.getyPos()));
			System.out.println("다시 입력하세요.");
			position = inputPosition();
		}
		Chess.Board.remove(this.position);
		this.position = position;
		Chess.Board.put(position, this);
		resetPath();
		moveCount++;
	}

	public void resetPath() {
		moveAblePosition.clear();
		addAccessiblePosition();
	}

	abstract void addAccessiblePosition();

	void printAccessiblePath() {
		Iterator<Position> iterator = moveAblePosition.iterator();
		while (iterator.hasNext()) {
			System.out.println("x좌표: " + iterator.next().getxPos());
			System.out.println("y좌표: " + iterator.next().getyPos());
		}
	}

	boolean isAddable(Position newPosition) {
		if (!isValidPosition(newPosition))
			return false;
		if (!isEmptyPlace(newPosition) && isSameTeam(newPosition))
			return false;
		return true;
	}
}