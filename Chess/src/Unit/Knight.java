package unit;

import system.Position;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
		unicodeForPrint = ((this.color == Color.WHITE) ? "\u2658" : "\u265E");
	}

	@Override
	void addAccessiblePosition(Position basePosition) {
		checkKnightPath(1, 2, basePosition);
		checkKnightPath(1, -2, basePosition);
		checkKnightPath(2, 1, basePosition);
		checkKnightPath(2, -1, basePosition);
		checkKnightPath(-1, 2, basePosition);
		checkKnightPath(-1, -2, basePosition);
		checkKnightPath(-2, -1,basePosition);
		checkKnightPath(-2, 1, basePosition);
	}

	private void checkKnightPath(int xScale, int yScale, Position basePosition) {
		int xPos = basePosition.getxPos() + xScale;
		int yPos = basePosition.getyPos() + yScale;
		Position position = new Position(xPos, yPos);

		if (isAddable(position)) {
			moveAblePositionList.add(position);
			attackAblePositionList.add(position);
		}
	}
}