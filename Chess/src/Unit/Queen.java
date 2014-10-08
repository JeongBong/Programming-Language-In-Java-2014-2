package unit;

import system.Position;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
		unicode = ((this.color == Color.WHITE) ? "\u2655" : "\u265B");
	}

	void addAccessiblePos(Position basePos) {
		checkBasicPath(1,0, basePos);
		checkBasicPath(-1,0, basePos);
		checkBasicPath(0,1, basePos);
		checkBasicPath(0,-1, basePos);
		checkBasicPath(1,1, basePos);
		checkBasicPath(1,-1, basePos);
		checkBasicPath(-1,-1, basePos);
		checkBasicPath(-1,1, basePos);
	}
}