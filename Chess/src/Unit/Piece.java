package Unit;

import java.util.ArrayList;
import java.util.Iterator;

import System.*;


abstract public class Piece extends Unit{
	final static int maxMoveSize =7;
	
	int moveCount;
	public String unicodeForPrint;
	public ArrayList<Position> moveAblePositionList = new ArrayList<Position>();
	public ArrayList<Position> attackAblePositionList = new ArrayList<Position>();

	Piece(Color color) {
		this.color = color;
		this.moveCount = 0;
	}

	// YG: Board 클래스를 따로 만들게 된다면 move 함수도 Board 클래스로 옮기는게 적절할 듯. move(Piece selected);

	public void resetMoveablePositionList(Position position_base) {
		moveAblePositionList.clear();
		addAccessiblePosition(position_base);
	}

	abstract void addAccessiblePosition(Position position_base);

	void printAccessiblePath() {
		Iterator<Position> iterator = moveAblePositionList.iterator();
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
	
	
	void checkPath_Basic(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos();
		int newyPos = position_base.getyPos();
		Position newPosition;

		for (int i = 0; i < maxMoveSize; i++) {
			newyPos += yScale;
			newxPos += xScale;
			newPosition = new Position(newxPos, newyPos);

			if (!isAddable(newPosition))
				break;
			moveAblePositionList.add(newPosition);
			attackAblePositionList.add(newPosition);
			if (!isEmptyPlace(newPosition) && !isSameTeam(newPosition))
				break;
		}

	}
	
	boolean isValidPosition(Position position) {
		if ((position.getxPos() != -1) && (position.getyPos() != -1))
			return true;
		return false;
	}

	boolean isEmptyPlace(Position position) {
		return !Board.chessBoard.containsKey(position);
	}
}