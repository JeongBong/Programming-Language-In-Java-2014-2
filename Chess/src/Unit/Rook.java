package unit;

import system.Position;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
		unicode = ((this.color == Color.WHITE) ? "\u2656" : "\u265C");
	}

	void addAccessiblePos(Position basePos) {
		checkBasicPath(1,0, basePos);
		checkBasicPath(-1,0, basePos);
		checkBasicPath(0,-1, basePos);
		checkBasicPath(0,1, basePos);
	}
}