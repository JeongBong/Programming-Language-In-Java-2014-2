package unit;

import system.Position;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
		unicodeForPrint = ((this.color == Color.WHITE) ? "\u2655" : "\u265B");
	}

	void addAccessiblePosition(Position basePosition) {
		checkBasicPath(1,0, basePosition);
		checkBasicPath(-1,0, basePosition);
		checkBasicPath(0,1, basePosition);
		checkBasicPath(0,-1, basePosition);
		checkBasicPath(1,1, basePosition);
		checkBasicPath(1,-1, basePosition);
		checkBasicPath(-1,-1, basePosition);
		checkBasicPath(-1,1, basePosition);
	}
}