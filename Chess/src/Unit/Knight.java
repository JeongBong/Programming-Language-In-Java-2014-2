package Unit;

import System.Position;

public class Knight extends Piece {

	public Knight(int xPosition, int yPosition, Identity id) {
		super(xPosition, yPosition, id);
	}

	@Override
	void addAccessiblePosition() {
		checkKnightPath(1, 2);
		checkKnightPath(1, -2);
		checkKnightPath(2, 1);
		checkKnightPath(2, -1);
		checkKnightPath(-1, 2);
		checkKnightPath(-1, -2);
		checkKnightPath(-2, -1);
		checkKnightPath(-2, 1);
	}

	private void checkKnightPath(int xScale, int yScale) {
		int newxPos = this.position.getxPos() + xScale;
		int newyPos = this.position.getyPos() + yScale;
		Position newPosition = new Position(newxPos, newyPos);

		if (isAddable(newPosition)) {
			moveAblePosition.add(newPosition);
			attackAblePosition.add(newPosition);
		}
	}
}