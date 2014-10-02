package Unit;

import java.util.InputMismatchException;
import java.util.Scanner;

import System.*;

abstract public class Unit {

	public Identity id;

	public enum Identity {

		WHITE_PLAYER(0), BLACK_PLAYER(1), WHITE_KING(2), BLACK_KING(3), WHITE_QUEEN(
				4), BLACK_QUEEN(5), WHITE_ROOK(6), BLACK_ROOK(7), WHITE_BISHOP(
				8), BLACK_BISHOP(9), WHITE_KNIGHT(10), BLACK_KNIGHT(11), WHITE_PAWN(
				12), BLACK_PAWN(13);

		private int no;

		private Identity(int no) {
			this.no = no;
		}

		public int getNo() {
			return no;
		}
	}

	public boolean isSameTeam(Position position) {
		return this.id.getNo() % 2 == Chess.Board.get(position).id.getNo() % 2;
	}

	Position inputPosition() {
		int xPosition;
		int yPosition;
		Scanner sc = new Scanner(System.in);
		Position position;
		try {
			System.out.print("X좌표: ");
			xPosition = sc.nextInt();
			System.out.print("Y좌표: ");
			yPosition = sc.nextInt();
			position = new Position(xPosition, yPosition);
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요.");
			position = inputPosition();
		}
		return position;
	}

	boolean isValidPosition(Position position) {
		if ((position.getxPos() != -1) && (position.getyPos() != -1))
			return true;
		return false;
	}

	boolean isEmptyPlace(Position position) {
		return !Chess.Board.containsKey(position);
	}
}