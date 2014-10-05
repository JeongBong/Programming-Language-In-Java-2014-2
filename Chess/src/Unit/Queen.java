package Unit;

import System.Position;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
		unicodeForPrint = (this.color.getNo() == 1) ? "\u2655" : "\u265B";
	}

	void addAccessiblePosition(Position position_base) {
		checkPath_Basic(1,0, position_base);
		checkPath_Basic(-1,0, position_base);
		checkPath_Basic(0,1, position_base);
		checkPath_Basic(0,-1, position_base);
		checkPath_Basic(1,1, position_base);
		checkPath_Basic(1,-1, position_base);
		checkPath_Basic(-1,-1, position_base);
		checkPath_Basic(-1,1, position_base);
	}
}