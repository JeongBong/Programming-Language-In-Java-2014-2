package Unit;

import System.Position;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
		unicodeForPrint = (this.color.getNo() == 1) ? "\u2658" : "\u265E";
	}

	@Override
	void addAccessiblePosition(Position position_base) {
		checkKnightPath(1, 2, position_base);
		checkKnightPath(1, -2, position_base);
		checkKnightPath(2, 1, position_base);
		checkKnightPath(2, -1, position_base);
		checkKnightPath(-1, 2, position_base);
		checkKnightPath(-1, -2, position_base);
		checkKnightPath(-2, -1,position_base);
		checkKnightPath(-2, 1, position_base);
	}

	private void checkKnightPath(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos() + xScale;
		int newyPos = position_base.getyPos() + yScale;
		Position newPosition = new Position(newxPos, newyPos);

		if (isAddable(newPosition)) {
			moveAblePositionList.add(newPosition);
			attackAblePositionList.add(newPosition);
		}
	}
}