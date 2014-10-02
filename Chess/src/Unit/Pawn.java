package Unit;

import System.Position;

public class Pawn extends Piece {

	final int isWhite = 1;
	final int isBlack = -1;

	public Pawn(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
		pieceColor = (this.id.getNo() % 2 == 0) ? isWhite : isBlack;
	}

	void addAccessiblePosition() {
		checkPawnPath(-1, 1 * pieceColor);
		checkPawnPath(1, 1 * pieceColor);

		if (moveCount == 0) {
			checkPawnPath(0, 2 * pieceColor);
			return;
		}
		checkPawnPath(0, 1 * pieceColor);
	}

	private void addAttackablePosition() {
		checkPawnAttackPath(-1, 1 * pieceColor);
		checkPawnAttackPath(1, 1 * pieceColor);
	}

	private void checkPawnAttackPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos() + xScale;
		int newyPos = this.position.getyPos() + pieceColor;
		Position newPosition;

		newPosition = new Position(newxPos, newyPos);

		if (isValidPosition(newPosition)) {
			attackAblePosition.add(newPosition);
		}
	}

	@Override
	public void resetPath() {
		moveAblePosition.clear();
		addAccessiblePosition();
		addAttackablePosition();
	}

	private void checkPawnPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos() + xScale;
		int newyPos = this.position.getyPos();
		Position newPosition;

		if (xScale != 0) {
			newyPos += pieceColor;
			newPosition = new Position(newxPos, newyPos);

			if (isValidPosition(newPosition) && !isEmptyPlace(newPosition)
					&& !isSameTeam(newPosition)) {
				moveAblePosition.add(newPosition);
			}
			return;
		}

		for (int i = 0; i < yScale * pieceColor; i++) {
			newyPos += pieceColor;
			newPosition = new Position(newxPos, newyPos);

			if (!isAddable(newPosition)) break;
			moveAblePosition.add(newPosition);
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