package unit;

import system.Position;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
		unicodeForPrint = ((this.color == Color.WHITE) ? "\u2657" : "\u265D");
	}

	protected void addAccessiblePosition(Position basePosition) {
		checkBasicPath(1, 1, basePosition);
		checkBasicPath(-1, 1, basePosition);
		checkBasicPath(1, -1, basePosition);
		checkBasicPath(-1, -1, basePosition);
	}
}