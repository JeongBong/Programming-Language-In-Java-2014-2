package unit;

import system.Position;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
		unicode = ((this.color == Color.WHITE) ? "\u2657" : "\u265D");
	}

	protected void addAccessiblePos(Position basePos) {
		checkBasicPath(1, 1, basePos);
		checkBasicPath(-1, 1, basePos);
		checkBasicPath(1, -1, basePos);
		checkBasicPath(-1, -1, basePos);
	}
}