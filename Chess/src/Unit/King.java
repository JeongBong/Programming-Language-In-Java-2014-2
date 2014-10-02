package Unit;

import System.*;

public class King extends Piece {

	public King(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
	}

	@Override
	void addAccessiblePosition() {
		checkKingPath(1, 1);
		checkKingPath(1, 0);
		checkKingPath(1, -1);
		checkKingPath(0, 1);
		checkKingPath(0, -1);
		checkKingPath(-1, 1);
		checkKingPath(-1, 0);
		checkKingPath(-1, -1);
	}

	private void checkKingPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos() + xScale;
		int newyPos = this.position.getyPos() + yScale;
		Position newPosition = new Position(newxPos, newyPos);

		if (isAddable(newPosition)) {
			moveAblePosition.add(newPosition);
			attackAblePosition.add(newPosition);
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
		if (Chess.attackAblePositionSet.contains(newPosition))
			return false;
		return true;
	}
}