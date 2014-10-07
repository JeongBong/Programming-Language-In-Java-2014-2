package unit;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import system.Board;
import system.Position;

public class Player extends Unit {
	private static final int LEFT = -1;
	private static final int RIGHT = 1;
	public boolean isChecked;
	public Position chosenPosition;

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

			if (!isSameTeam(position) || Board.chessBoard.get(position).moveAblePositionList.isEmpty()) {
				System.out.println("움직일 수 있는 체스말이 아닙니다. 다시 선택하세요.");
				position = inputPosition();
				continue;
			}

			chosenPosition = position;
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
			if(isKing(chosenPiece) && isCastling(gonnaMovePosition)) break;
			
			System.out.println("접근할 수 없는 위치입니다. 다시 입력해주세요.");
			gonnaMovePosition = inputPosition();
			
		}

		gonnaMovePiece = Board.chessBoard.get(chosenPosition);

		if(isEnpassant(gonnaMovePiece, gonnaMovePosition)){
			Position baseBeside = new Position(gonnaMovePosition.getxPos(),gonnaMovePosition.getyPos()-1);
			Board.chessBoard.remove(baseBeside);
		}
		
		isPromotion();
		
		
		Board.chessBoard.put(gonnaMovePosition, gonnaMovePiece);

		gonnaMovePiece.resetMoveablePositionList(gonnaMovePosition);
		gonnaMovePiece.moveCount++;

		Board.chessBoard.remove(chosenPosition);
	}

	private boolean isCastling(Position gonnaMovePosition) {
		Piece rook;
		int baseYPos = (this.color == Color.WHITE) ? 0:7;
		
		if(isCastlingPossible(gonnaMovePosition, baseYPos, LEFT)){ 
			Position rookInitPos =  new Position(0, baseYPos);
			Position rookCastledPos = new Position(2, baseYPos);
			
			rook = Board.chessBoard.get(rookInitPos);
			Board.chessBoard.put(rookCastledPos, rook);
			rook.resetMoveablePositionList(rookCastledPos);
			Board.chessBoard.remove(rookInitPos);
		return true;
		}
		
		if(isCastlingPossible(gonnaMovePosition, baseYPos, RIGHT)){ 
			Position rookInitPos =  new Position(7, baseYPos);
			Position rookCastledPos = new Position(4, baseYPos);

			rook = Board.chessBoard.get(rookInitPos);
			Board.chessBoard.put(rookCastledPos, Board.chessBoard.get(rookInitPos));
			rook.resetMoveablePositionList(rookCastledPos);
			Board.chessBoard.remove(rookInitPos);
		return true;
		}

	return false;
	}

	private boolean isKing(Piece kingCandidate){		
		if(kingCandidate.moveCount!=0) return false;
		if(!(kingCandidate.getUnicodeForPrint() == "\u2654" || kingCandidate.getUnicodeForPrint() == "\u265A")) return false;	
		return true;
	}
	
	private boolean isCastlingPossible(Position gonnaMovePos, int baseYPos, int direction){
		Position rookInitPos = (direction == 1) ? new Position(7, baseYPos) : new Position(0, baseYPos);
		Position palace = (direction == 1) ? new Position(5, baseYPos) : new Position(1, baseYPos);

		if(Board.chessBoard.containsKey(rookInitPos)){ 
			Piece rookCandidate = Board.chessBoard.get(rookInitPos);
			if(rookCandidate.moveCount!=0) return false;
			if(!(rookCandidate.getUnicodeForPrint() == "\u2656" || rookCandidate.getUnicodeForPrint() == "\u265C")) return false;
			
			if(isCastleLocationSafe(baseYPos, direction) && palace.equals(gonnaMovePos)) return true;
		}	
		
	return false;
	}
	
	private boolean isCastleLocationSafe(int baseYPos, int direction){		

		Position rookInitPos = (direction == 1) ? new Position(7, baseYPos) : new Position(0, baseYPos);
		int xPos = 3+direction;
		Position gonnaInspectPos = new Position(xPos, baseYPos);
		
		while(!rookInitPos.equals(gonnaInspectPos)){
			//킹과 캐슬 사이는 비어있으며, 적의 공격과 이동이 불가해야 한다. 
			if(Board.chessBoard.containsKey(gonnaInspectPos)) return false;
			if(Board.moveAblePositionSet.contains(gonnaInspectPos)) return false;
			xPos+=direction;
			gonnaInspectPos = new Position(xPos, baseYPos);
		}		
		return true;
	}
	
	
	private boolean isEnpassant(Piece gonnaMovePiece, Position gonnaMovePosition) {
		if (gonnaMovePiece.unicodeForPrint == "\u2659"|| gonnaMovePiece.unicodeForPrint == "\u265F") {
			if (isEmptyPlace(gonnaMovePosition) && gonnaMovePiece.attackAblePositionList.contains(gonnaMovePosition)) {
				return true;
			}
		}
		return false;
	}
	
	private void isPromotion() {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();

		while (pieceIter.hasNext()) {
			Position position = (Position) pieceIter.next();
			Piece inspectedPiece = Board.chessBoard.get(position);
			if (!isSameTeam(position))
				continue;
			if (!(inspectedPiece.getUnicodeForPrint() == "\u2659") || (inspectedPiece.getUnicodeForPrint() == "\u265F"))
				continue;
			if (position.getyPos() == 7 || position.getyPos() == 0)
				executePromotion(position);
				return;
		}
		return;
	}

	private void executePromotion(Position position) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Piece gonnaTransPiece = Board.chessBoard.get(position);
		Color color = gonnaTransPiece.color;
		
		System.out.println("어떤 piece로 바꾸시겠습니까? 1.퀸  2.룩  3.비숍  4.나이트" );
		
		switch(sc.nextInt()){
		case 1 :
			Board.chessBoard.put(position, new Queen(color));
			break;
		case 2 :
			Board.chessBoard.put(position, new Rook(color));
			break;
		case 3 :
			Board.chessBoard.put(position, new Bishop(color));
			break;
		case 4 :
			Board.chessBoard.put(position, new Knight(color));
			break;			
		}
	Board.chessBoard.get(position).resetMoveablePositionList(position);
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