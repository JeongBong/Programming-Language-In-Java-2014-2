package Unit;

import System.Position;

public class Queen extends Piece {

	public Queen(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
	}

	void addAccessiblePosition() {
		checkRookPath(1, 0);
		checkRookPath(-1, 0);
		checkRookPath(0, -1);
		checkRookPath(0, 1);
		checkBishopPath(1, 1);
		checkBishopPath(1, -1);
		checkBishopPath(-1, -1);
		checkBishopPath(-1, 1);
	}

	private void checkRookPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos();
		int newyPos = this.position.getyPos();
		Position newPosition;

		for (int i = 0; i < maxMoveSize; i++) {
			newyPos += yScale;
			newxPos += xScale;
			newPosition = new Position(newxPos, newyPos);

			if (!isAddable(newPosition))
				break;
			moveAblePosition.add(newPosition);
			attackAblePosition.add(newPosition);
			if (!isEmptyPlace(newPosition) && !isSameTeam(newPosition))
				break;
		}

	}

	private void checkBishopPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos();
		int newyPos = this.position.getyPos();
		Position newPosition;

		for (int i = 0; i < maxMoveSize; i++) {
			newyPos += xScale;
			newxPos += yScale;
			newPosition = new Position(newxPos, newyPos);

			if (!isAddable(newPosition))
				break;
			moveAblePosition.add(newPosition);
			attackAblePosition.add(newPosition);
			if (!isEmptyPlace(newPosition) && !isSameTeam(newPosition))
				break;
		}
	}
}
