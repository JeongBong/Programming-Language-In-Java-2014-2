package Unit;

import System.*;

public class King extends Piece {

	public King(Color color) {
		super(color);
		unicodeForPrint = (color.getNo()==1) ? "\u2654" : "\u265A";
	}

	// YG: King이나 Knight는 이렇게 일일이 다 확인할거라면 addAccessiblePosition이랑 check-Path 두 함수가 나누어질 필요가 없음. 
	@Override
	void addAccessiblePosition(Position position_base) {
		checkKingPath(1, 1, position_base);
		checkKingPath(1, 0, position_base);
		checkKingPath(1, -1, position_base);
		checkKingPath(0, 1, position_base);
		checkKingPath(0, -1, position_base);
		checkKingPath(-1, 1, position_base);
		checkKingPath(-1, 0, position_base);
		checkKingPath(-1, -1, position_base);
	}

	private void checkKingPath(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos() + xScale;
		int newyPos = position_base.getyPos() + yScale;
		Position newPosition = new Position(newxPos, newyPos);

		if (isAddable(newPosition)) {
			moveAblePositionList.add(newPosition);
			attackAblePositionList.add(newPosition);
		}
	}

	@Override
	protected boolean isAddable(Position newPosition) {
		if (!isValidPosition(newPosition))
			return false;
		if (!isEmptyPlace(newPosition) && isSameTeam(newPosition))
			return false;
		if (!isSafePlace(newPosition))
			return false;

		return true;
	}

	private boolean isSafePlace(Position newPosition) {
		if (Board.attackAblePositionSet.contains(newPosition))
			return false;
		return true;
	}
}