package unit;

import system.*;

public class King extends Piece {

	public King(Color color) {
		super(color);
		unicodeForPrint = (this.color == Color.WHITE) ? "\u2654" : "\u265A";
	}

	// YG: King이나 Knight는 이렇게 일일이 다 확인할거라면 addAccessiblePosition이랑 check-Path 두 함수가 나누어질 필요가 없음. 
	@Override
	void addAccessiblePosition(Position basePosition) {
		checkKingPath(1, 1, basePosition);
		checkKingPath(1, 0, basePosition);
		checkKingPath(1, -1, basePosition);
		checkKingPath(0, 1, basePosition);
		checkKingPath(0, -1, basePosition);
		checkKingPath(-1, 1, basePosition);
		checkKingPath(-1, 0, basePosition);
		checkKingPath(-1, -1, basePosition);
	}

	private void checkKingPath(int xScale, int yScale, Position basePosition) {
		int xPos = basePosition.getxPos() + xScale;
		int yPos = basePosition.getyPos() + yScale;
		Position position = new Position(xPos, yPos);

		if (isAddable(position)) {
			moveAblePositionList.add(position);
			attackAblePositionList.add(position);
		}
	}

	@Override
	protected boolean isAddable(Position position) {
		if (!isValidPosition(position))
			return false;
		if (!isEmptyPlace(position) && isSameTeam(position))
			return false;
		if (!isSafePlace(position))
			return false;

		return true;
	}

	private boolean isSafePlace(Position position) {
		if (Board.attackAblePositionSet.contains(position))
			return false;
		return true;
	}
}