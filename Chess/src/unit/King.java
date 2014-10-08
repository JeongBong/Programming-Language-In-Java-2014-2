package unit;

import system.*;

public class King extends Piece {

	public King(Color color) {
		super(color);
		unicode = (this.color == Color.WHITE) ? "\u2654" : "\u265A";
	}

	@Override
	void addAccessiblePos(Position basePos) {
		// YG: 2중 for문으로 줄여봅시다.
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
		// YG: Piece의 isAddable과 중복되는 내용은
		// YG: super.isAddable로 중복 제거할 수 있음
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