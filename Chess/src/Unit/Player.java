package unit;

import java.util.InputMismatchException;
import java.util.Scanner;

import system.Board;
import system.Position;

public class Player extends Unit {
	public boolean isChecked;
	public Position gonnaRemovePosition;

	public Player(Color color) {
		this.color = color;
		isChecked = false;
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

			gonnaRemovePosition = position;
			chosenPiece = Board.chessBoard.get(position);
			chosenPiece.resetMoveablePositionList(position);
			return chosenPiece;
		}
	}

	public void movePiece(Piece chosenPiece) {
		Piece gonnaMovePiece;
		System.out.println("움직일 위치의 좌표값을 입력하세요.");
		Position gonnaMovePosition = inputPosition();

		while (!chosenPiece.moveAblePositionList.contains(gonnaMovePosition)) {
			System.out.println("접근할 수 없는 위치입니다. 다시 입력해주세요.");
			gonnaMovePosition = inputPosition();
		}

		gonnaMovePiece = Board.chessBoard.get(gonnaRemovePosition);

		executeEnpassant(gonnaMovePiece, gonnaMovePosition);//앙파상인지 확인하여 처리
		Board.chessBoard.put(gonnaMovePosition, gonnaMovePiece);

		gonnaMovePiece.resetMoveablePositionList(gonnaMovePosition);
		gonnaMovePiece.moveCount++;

		Board.chessBoard.remove(gonnaRemovePosition);

	}

	private void executeEnpassant(Piece gonnaMovePiece, Position gonnaMovePosition) {
		Position beside;
		if (gonnaMovePiece.unicodeForPrint == "\u2659"|| gonnaMovePiece.unicodeForPrint == "\u265F") {
			if (isEmptyPlace(gonnaMovePosition) && gonnaMovePiece.attackAblePositionList.contains(gonnaMovePosition)) {
				//공격가능한 리스트에 없었는데 이동했다면, 앙파상.
				beside = new Position(gonnaMovePosition.getxPos(),gonnaRemovePosition.getyPos());
				Board.chessBoard.remove(beside);
			}
		}
	}

	@SuppressWarnings("resource")
	Position inputPosition() {
		Position position;
		int xPosition;
		int yPosition;
		Scanner sc = new Scanner(System.in);

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