package Unit;

import System.Position;

public class Pawn extends Piece {
	int direction;

	public Pawn(Color color) {
		super(color);
		direction = color.getNo();
		unicodeForPrint = (this.color.getNo() == 1) ? "\u2659" : "\u265F";
	}

	void addAccessiblePosition(Position position_base) {
		checkPawnPath(-1, 1 * direction,position_base);
		checkPawnPath(1, 1 * direction,position_base);

		if (moveCount == 0) {
			checkPawnPath(0, 2 * direction,position_base);
			return;
		}
		checkPawnPath(0, 1 * direction,position_base);
	}

	private void addAttackablePosition(Position position_base) {
		checkPawnAttackPath(-1, 1 * direction,position_base);
		checkPawnAttackPath(1, 1 * direction,position_base);
	}

	private void checkPawnAttackPath(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos() + xScale;
		int newyPos = position_base.getyPos() + yScale;
		Position newPosition;

		newPosition = new Position(newxPos, newyPos);

		if (isValidPosition(newPosition)) {
			attackAblePositionList.add(newPosition);
		}
	}

	@Override
	public void resetMoveablePositionList(Position position_base) {
		moveAblePositionList.clear();
		addAccessiblePosition(position_base);
		addAttackablePosition(position_base);
	}

	private void checkPawnPath(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos() + xScale;
		int newyPos = position_base.getyPos();
		Position newPosition;

		if (xScale != 0) {
			newyPos += direction;
			newPosition = new Position(newxPos, newyPos);

			if (isValidPosition(newPosition) && !isEmptyPlace(newPosition)
					&& !isSameTeam(newPosition)) {
				moveAblePositionList.add(newPosition);
			}
			return;
		}

		for (int i = 0; i < yScale * direction; i++) {
			newyPos += direction;
			newPosition = new Position(newxPos, newyPos);

			if (!isAddable(newPosition)) break;
			moveAblePositionList.add(newPosition);
			if (!isEmptyPlace(newPosition)) break;
		}
	}

	@Override
	protected boolean isAddable(Position newPosition) {
		if (!isValidPosition(newPosition))
			return false;
		if (!isEmptyPlace(newPosition))
			return false;

		return true;
	}

}