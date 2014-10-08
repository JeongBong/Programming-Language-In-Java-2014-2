package unit;

import system.Position;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
		unicode = ((this.color == Color.WHITE) ? "\u2658" : "\u265E");
	}

	@Override
	void addAccessiblePos(Position basePos) {
		checkKnightPath(1, 2, basePos);
		checkKnightPath(1, -2, basePos);
		checkKnightPath(2, 1, basePos);
		checkKnightPath(2, -1, basePos);
		checkKnightPath(-1, 2, basePos);
		checkKnightPath(-1, -2, basePos);
		checkKnightPath(-2, -1,basePos);
		checkKnightPath(-2, 1, basePos);
	}

	private void checkKnightPath(int xScale, int yScale, Position basePos) {
		int xPos = basePos.getxPos() + xScale;
		int yPos = basePos.getyPos() + yScale;
		Position checkedPos = new Position(xPos, yPos);

		if (isAddable(checkedPos)) {
			moveAblePosList.add(checkedPos);
			attackAblePosList.add(checkedPos);
		}
	}
}