package system;

import java.util.Iterator;
import java.util.Scanner;

import unit.Bishop;
import unit.Knight;
import unit.Piece;
import unit.Player;
import unit.Queen;
import unit.Rook;
import unit.Unit.Color;

public class PlayManager {

	Player whitePlayer;
	Player blackPlayer;
	boolean isCheck;
	public boolean isCheckmate;

	public PlayManager() {
		whitePlayer = new Player(Color.WHITE);
		blackPlayer = new Player(Color.BLACK);
		isCheckmate = false;
	}
	
	//startChess는 플레이어 한 명의 턴을 단위로 while loop를 실행한다.
	public void playChess() {
		Piece chosenPiece;
		Player activatedPlayer = blackPlayer;

		while (!isCheckmate) {
			activatedPlayer = (activatedPlayer.color.getNo() == 1) ? blackPlayer : whitePlayer;
			updateGameInfo(activatedPlayer);
			
			checkSpecialCase(activatedPlayer);
			
			chosenPiece = activatedPlayer.choosePiece();
			activatedPlayer.movePiece(chosenPiece);
			
			if(isCheck&&judgeCheck(activatedPlayer)){
				System.out.println(activatedPlayer.color + "플레이어가 졌습니다.");
				isCheckmate = true;
			}
		}
	}

	private void updateGameInfo(Player player) {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		BoardManager boardManager = new BoardManager();

		Board.attackAblePositionSet.clear();

		while (pieceIter.hasNext()) {
			Position position = (Position) pieceIter.next();
			updateAttackAblePosition(player, position);
		}
		boardManager.printBoard();
	}
	
	private void updateAttackAblePosition(Player player, Position position) {

		Piece piece = Board.chessBoard.get(position);

		if (player.isSameTeam(position)) return;
		Board.attackAblePositionSet.addAll(piece.attackAblePositionList);
	}

	
	private void checkSpecialCase(Player player){
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		
		while(pieceIter.hasNext()){
			Position piecePos = (Position) pieceIter.next();
			
			if(judgePromotion(player, piecePos)){
				System.out.println("프로모션입니다.");
				executePromotion(piecePos);
			}
		
		}
		if(judgeCheck(player)){
			isCheck = true;
			System.out.println("체크입니다. 심사숙고하세요.");
		}
				
	}

	private boolean judgeCheck(Player player) {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		
		while(pieceIter.hasNext()){
			Position isValidWhenKing = (Position) pieceIter.next();
			Piece inspectedPiece = Board.chessBoard.get(isValidWhenKing);

			if (!player.isSameTeam(isValidWhenKing))
				continue;
			if (!(inspectedPiece.getUnicodeForPrint() == "\u2654" || inspectedPiece.getUnicodeForPrint() == "\u265A")) //HACK: 유니코드는 여기 쓰는게 아니에요.
				continue;
			if (Board.attackAblePositionSet.contains(isValidWhenKing)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean judgePromotion(Player player, Position isValidWhenPawn){
		Piece inspectedPiece = Board.chessBoard.get(isValidWhenPawn);

		if(!player.isSameTeam(isValidWhenPawn))
			return false;
		if(!(inspectedPiece.getUnicodeForPrint() == "\u2659") || (inspectedPiece.getUnicodeForPrint() == "\u265F"))
			return false;
		if(isValidWhenPawn.getyPos()==7||isValidWhenPawn.getyPos()==0)
			return true;
		
		return false;
		
	}
	
	private void executePromotion(Position piecePos) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Color pieceColor = Board.chessBoard.get(piecePos).color;
		Board.chessBoard.remove(piecePos);
		
		System.out.println("어떤 piece로 바꾸시겠습니까? 1.퀸  2.룩  3.비숍  4.나이" );
		
		switch(sc.nextInt()){
		case 1 :
			Board.chessBoard.put(piecePos, new Queen(pieceColor));
			break;
		case 2 :
			Board.chessBoard.put(piecePos, new Rook(pieceColor));
			break;
		case 3 :
			Board.chessBoard.put(piecePos, new Bishop(pieceColor));
			break;
		case 4 :
			Board.chessBoard.put(piecePos, new Knight(pieceColor));
			break;			
		}		
	}

}