package unit;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import system.Board;
import system.Position;

// YG: Player가 Unit을 상속받아야 할 이유가...?
public class Player extends Unit {
	private static final int LEFT = -1;
	private static final int RIGHT = 1;
	public boolean isChecked;
	public Position chosenPos;

	public Player(Color color) {
		this.color = color;
		isChecked = false;
	}

	public Piece choosePiece() {
		Piece chosenPiece;

		System.out.println("움직일 체스말의 좌표값을 입력하세요.");
		Position pos = inputPos();

		while (true) {
			if (!Board.chessBoard.containsKey(pos)) {
				System.out.println("선택한 위치에 체스말이 없습니다. 다시 선택하세요.");
				pos = inputPos();
				continue;
			}
			Board.chessBoard.get(pos).resetMoveablePosList(pos);

			if (!isSameTeam(pos) || Board.chessBoard.get(pos).moveAblePosList.isEmpty()) {
				System.out.println("움직일 수 있는 체스말이 아닙니다. 다시 선택하세요.");
				pos = inputPos();
				continue;
			}

			chosenPos = pos;
			chosenPiece = Board.chessBoard.get(pos);
			chosenPiece.resetMoveablePosList(pos);
		
			return chosenPiece;
		}
	}

	public void movePiece(Piece chosenPiece) {
		Piece gonnaMovePiece;
		System.out.println("움직일 위치의 좌표값을 입력하세요.");
		Position gonnaMovePos = inputPos();

		while (!chosenPiece.moveAblePosList.contains(gonnaMovePos)) {
			
			if(isKing(chosenPiece) && isCastling(gonnaMovePos)) break; //캐슬링 처리
			
			System.out.println("접근할 수 없는 위치입니다. 다시 입력해주세요.");
			gonnaMovePos = inputPos();
			
		}

		gonnaMovePiece = Board.chessBoard.get(chosenPos);

		if(isEnpassant(gonnaMovePiece, gonnaMovePos)){ //앙파상 처리
			Position baseBeside = new Position(gonnaMovePos.getxPos(),gonnaMovePos.getyPos()-1);
			Board.chessBoard.remove(baseBeside);
		}
		
		Board.chessBoard.put(gonnaMovePos, gonnaMovePiece);

		gonnaMovePiece.resetMoveablePosList(gonnaMovePos);
		gonnaMovePiece.moveCount++;
		Board.chessBoard.remove(chosenPos);
		
		isPromotion();//HACK: 프로모션의 처리, 명확하지 못하다. 해결방법을 잘 모르겠다. 
	}

	private boolean isCastling(Position gonnaMovePos) {
		Piece rook;
		int baseYPos = (this.color == Color.WHITE) ? 0:7;
		
		if(isCastlingPossible(gonnaMovePos, baseYPos, LEFT)){ 
			Position rookInitPos =  new Position(0, baseYPos);
			Position rookCastledPos = new Position(2, baseYPos);
			
			rook = Board.chessBoard.get(rookInitPos);
			Board.chessBoard.put(rookCastledPos, rook);
			rook.resetMoveablePosList(rookCastledPos);
			Board.chessBoard.remove(rookInitPos);
		return true;
		}
		
		if(isCastlingPossible(gonnaMovePos, baseYPos, RIGHT)){ 
			Position rookInitPos =  new Position(7, baseYPos);
			Position rookCastledPos = new Position(4, baseYPos);

			rook = Board.chessBoard.get(rookInitPos);
			Board.chessBoard.put(rookCastledPos, Board.chessBoard.get(rookInitPos));
			rook.resetMoveablePosList(rookCastledPos);
			Board.chessBoard.remove(rookInitPos);
		return true;
		}

	return false;
	}

	private boolean isKing(Piece kingCandidate){		
		if(kingCandidate.moveCount!=0) return false;
		if(!(kingCandidate.unicode == "\u2654" || kingCandidate.unicode == "\u265A")) return false;	
		return true;
	}
	
	private boolean isCastlingPossible(Position gonnaMovePos, int baseYPos, int direction){
		Position rookInitPos = (direction == 1) ? new Position(7, baseYPos) : new Position(0, baseYPos);
		Position palace = (direction == 1) ? new Position(5, baseYPos) : new Position(1, baseYPos);

		if(Board.chessBoard.containsKey(rookInitPos)){ 
			Piece rookCandidate = Board.chessBoard.get(rookInitPos);
			if(rookCandidate.moveCount!=0) return false;
			if(!(rookCandidate.unicode == "\u2656" || rookCandidate.unicode == "\u265C")) return false;
			
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
			if(Board.moveAblePosSet.contains(gonnaInspectPos)) return false;
			xPos+=direction;
			gonnaInspectPos = new Position(xPos, baseYPos);
		}		
		return true;
	}
	
	
	private boolean isEnpassant(Piece gonnaMovePiece, Position gonnaMovePos) {
		if (gonnaMovePiece.unicode == "\u2659"|| gonnaMovePiece.unicode == "\u265F") {
			if (isEmptyPlace(gonnaMovePos) && gonnaMovePiece.attackAblePosList.contains(gonnaMovePos)) {
				return true;
			}
		}
		return false;
	}
	
	private void isPromotion() {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();

		while (pieceIter.hasNext()) {
			Position pos = (Position) pieceIter.next();
			Piece inspectedPiece = Board.chessBoard.get(pos);
			if (!isSameTeam(pos)){
				continue;
			}
			if (!(inspectedPiece.unicode == "\u2659" || inspectedPiece.unicode == "\u265F")){
				continue;
			}
			if (pos.getyPos()== 7 || pos.getyPos() == 0){ //pawn이 맨 아래 혹은 맨 위에 있을때.
				executePromotion(pos);
			return;
			}
		}
		return;
	}

	private void executePromotion(Position pos) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Piece gonnaTransPiece = Board.chessBoard.get(pos);
		Color color = gonnaTransPiece.color;
		
		System.out.println("어떤 piece로 바꾸시겠습니까? 1.퀸  2.룩  3.비숍  4.나이트" );
		
		switch(sc.nextInt()){
		case 1 :
			Board.chessBoard.put(pos, new Queen(color));
			break;
		case 2 :
			Board.chessBoard.put(pos, new Rook(color));
			break;
		case 3 :
			Board.chessBoard.put(pos, new Bishop(color));
			break;
		case 4 :
			Board.chessBoard.put(pos, new Knight(color));
			break;			
		}
	Board.chessBoard.get(pos).resetMoveablePosList(pos);
	}

	@SuppressWarnings("resource")
	Position inputPos() {
		Position pos;
		int xPos;
		int yPos;
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("X좌표: ");
			xPos = sc.nextInt();
			System.out.print("Y좌표: ");
			yPos = sc.nextInt();
			pos = new Position(xPos, yPos);
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요.");
			pos = inputPos();
		}
		return pos;
	}

}