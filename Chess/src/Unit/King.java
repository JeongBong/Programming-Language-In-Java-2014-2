package unit;

import system.*;

public class King extends Piece {

	public King(Color color) {
		super(color);
		unicode = (this.color == Color.WHITE) ? "\u2654" : "\u265A";
	}

	// YG: King이나 Knight는 이렇게 일일이 다 확인할거라면 addAccessiblePosition이랑 check-Path 두 함수가 나누어질 필요가 없음. 
	@Override
	void addAccessiblePos(Position basePos) {
		checkKingPath(1, 1, basePos);
		checkKingPath(1, 0, basePos);
		checkKingPath(1, -1, basePos);
		checkKingPath(0, 1, basePos);
		checkKingPath(0, -1, basePos);
		checkKingPath(-1, 1, basePos);
		checkKingPath(-1, 0, basePos);
		checkKingPath(-1, -1, basePos);
	}

	private void checkKingPath(int xScale, int yScale, Position basePos) {
		int xPos = basePos.getxPos() + xScale;
		int yPos = basePos.getyPos() + yScale;
		Position checkedPos = new Position(xPos, yPos);

		if (isAddable(checkedPos)) {
			moveAblePosList.add(checkedPos);
			attackAblePosList.add(checkedPos);
		}
	}

	@Override
	protected boolean isAddable(Position pos) {
		if (!isValidPos(pos))
			return false;
		if (!isEmptyPlace(pos) && isSameTeam(pos))
			return false;
		if (!isSafePlace(pos))
			return false;

		return true;
	}

	private boolean isSafePlace(Position pos) {
		if (Board.attackAblePosSet.contains(pos))
			return false;
		return true;
	}
}