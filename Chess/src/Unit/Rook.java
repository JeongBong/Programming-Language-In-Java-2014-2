package Unit;

import System.Position;

public class Rook extends Piece {

	public Rook(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
	}

	void addAccessiblePosition() {
		checkRookPath(1, 0);
		checkRookPath(-1, 0);
		checkRookPath(0, -1);
		checkRookPath(0, 1);
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

}