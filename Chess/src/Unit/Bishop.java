package Unit;

import System.Position;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
		unicodeForPrint = (this.color.getNo() == 1) ? "\u2657" : "\u265D";
	}

	protected void addAccessiblePosition(Position position_base) {
		checkPath_Basic(1, 1, position_base);
		checkPath_Basic(-1, 1, position_base);
		checkPath_Basic(1, -1, position_base);
		checkPath_Basic(-1, -1, position_base);
	}
}