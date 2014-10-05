package Unit;

import System.Position;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
		unicodeForPrint = (this.color.getNo() == 1) ? "\u2656" : "\u265C";
	}

	void addAccessiblePosition(Position position_base) {
		checkPath_Basic(1,0, position_base);
		checkPath_Basic(-1,0, position_base);
		checkPath_Basic(0,-1, position_base);
		checkPath_Basic(0,1, position_base);
	}
}