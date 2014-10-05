package Unit;

import java.util.InputMismatchException;
import java.util.Scanner;

import System.*;

public class Player extends Unit {
	public boolean check;
	public Position position_gonnaRemove;

	public Player(Color black) {
		this.color = black;
		check = false;
	}

	public Piece choosePiece() {
		Piece chosenPiece;

		System.out.println("움직일 체스말의 좌표값을 입력하세요.");
		Position position = inputPosition();

		while (true) {
			if (!Board.chessBoard.containsKey(position)) {
				System.out.println("선택한 위치에 체스말이 없습니다. 다시 선택하세요.");
				position = inputPosition();
				continue;
			}
			Board.chessBoard.get(position).resetMoveablePositionList(position);

			if (!isSameTeam(position)
					|| Board.chessBoard.get(position).moveAblePositionList
							.isEmpty()) {
				System.out.println("움직일 수 있는 체스말이 아닙니다. 다시 선택하세요.");
				position = inputPosition();
				continue;
			}

			position_gonnaRemove = position;
			chosenPiece = Board.chessBoard.get(position);
			chosenPiece.resetMoveablePositionList(position);
			return chosenPiece;
		}
	}

	public void movePiece(Piece piece_chosen) {
		Piece piece_gonnaMove;

		System.out.println("움직일 위치의 좌표값을 입력하세요.");
		Position position_gonnaMove = inputPosition();

		while (!piece_chosen.moveAblePositionList.contains(position_gonnaMove)) {
			System.out.println("접근할 수 없습니다.");
			System.out.println("다시 입력하세요.");
			position_gonnaMove = inputPosition();
		}

		piece_gonnaMove = Board.chessBoard.get(position_gonnaRemove);
		Board.chessBoard.put(position_gonnaMove, piece_gonnaMove);

		piece_gonnaMove.resetMoveablePositionList(position_gonnaMove);
		piece_gonnaMove.moveCount++;
		Board.chessBoard.remove(position_gonnaRemove);

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
}