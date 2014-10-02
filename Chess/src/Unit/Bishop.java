package Unit;

import System.Position;

public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
	}

	protected void addAccessiblePosition() {
		checkBishopPath(1, 1);
		checkBishopPath(1, -1);
		checkBishopPath(-1, -1);
		checkBishopPath(-1, 1);
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