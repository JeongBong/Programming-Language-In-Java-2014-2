package unit;

import java.util.ArrayList;

import system.Position;

abstract public class Piece extends Unit{
	final static int maxMoveSize =7; //체스판의 크기가 8칸 이므로, 최대로 움직일 수 있는 거리는 7

	
	int moveCount;
	public String unicode;
	public ArrayList<Position> moveAblePosList = new ArrayList<Position>();
	public ArrayList<Position> attackAblePosList = new ArrayList<Position>();

	Piece(Color color) {
		this.color = color;
		this.moveCount = 0;
	}

	public void resetMoveablePosList(Position basePos) {
		moveAblePosList.clear();
		addAccessiblePos(basePos);
	}

	abstract void addAccessiblePos(Position basePos);

	boolean isAddable(Position checkedPos) {
		if (!isValidPos(checkedPos))
			return false;
		if (!isEmptyPlace(checkedPos) && isSameTeam(checkedPos))
			return false;
		return true;
	}
	
	//basePosition에서 가로/세로 방향으로 움직일 거리를 입력하여 이동가능한 경로. 퀸/룩/비숍이 사용
	void checkBasicPath(int xScale, int yScale, Position basePos) {
		int xPos = basePos.getxPos();
		int yPos = basePos.getyPos();
		Position checkedPos;

		for (int i = 0; i < maxMoveSize; i++) {
			yPos += yScale;
			xPos += xScale;
			checkedPos = new Position(xPos, yPos);

			if (!isAddable(checkedPos))
				break;
			moveAblePosList.add(checkedPos);
			attackAblePosList.add(checkedPos);
			if (!isEmptyPlace(checkedPos) && !isSameTeam(checkedPos))
				break;
		}

	}
}