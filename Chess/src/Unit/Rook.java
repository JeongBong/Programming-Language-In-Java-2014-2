package unit;

import system.Position;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
		unicodeForPrint = ((this.color == Color.WHITE) ? "\u2656" : "\u265C");
	}

	void addAccessiblePosition(Position basePosition) {
		checkBasicPath(1,0, basePosition);
		checkBasicPath(-1,0, basePosition);
		checkBasicPath(0,-1, basePosition);
		checkBasicPath(0,1, basePosition);
	}
}