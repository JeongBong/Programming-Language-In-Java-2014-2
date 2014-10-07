package unit;

import java.util.ArrayList;

import system.Board;
import system.Position;

abstract public class Piece extends Unit{
	final static int maxMoveSize =7; //체스판의 크기가 8칸 이므로, 최대로 움직일 수 있는 거리는 7

	private static final int INPUT_MISTAKE = -1;
	
	int moveCount;
	String unicodeForPrint;
	public ArrayList<Position> moveAblePositionList = new ArrayList<Position>();
	public ArrayList<Position> attackAblePositionList = new ArrayList<Position>();

	Piece(Color color) {
		this.color = color;
		this.moveCount = 0;
	}

	public void resetMoveablePositionList(Position basePosition) {
		moveAblePositionList.clear();
		addAccessiblePosition(basePosition);
	}

	abstract void addAccessiblePosition(Position basePosition);

	boolean isAddable(Position position) {
		if (!isValidPosition(position))
			return false;
		if (!isEmptyPlace(position) && isSameTeam(position))
			return false;
		return true;
	}
	
	//basePosition에서 가로/세로 방향으로 움직일 거리를 입력하여 이동가능한 경로. 퀸/룩/비숍이 사용
	void checkBasicPath(int xScale, int yScale, Position basePosition) {
		int xPos = basePosition.getxPos();
		int yPos = basePosition.getyPos();
		Position position;

		for (int i = 0; i < maxMoveSize; i++) {
			yPos += yScale;
			xPos += xScale;
			position = new Position(xPos, yPos);

			if (!isAddable(position))
				break;
			moveAblePositionList.add(position);
			attackAblePositionList.add(position);
			if (!isEmptyPlace(position) && !isSameTeam(position))
				break;
		}

	}
	
	//INPUT_MISTAKE인 경우는 보드의 범위를 벗어나는 경우
	boolean isValidPosition(Position position) {
		if ((position.getxPos() != INPUT_MISTAKE) && (position.getyPos() != INPUT_MISTAKE))
			return true;
		return false;
	}



	public String getUnicodeForPrint() {
		return unicodeForPrint;
	}
}