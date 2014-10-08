package system;

import java.util.Iterator;

import unit.Piece;
import unit.Player;
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
		isCheck = false;
	}
	
	//startChess는 플레이어 한 명의 턴을 단위로 while loop를 실행한다.
	public void playChess() {
		Piece chosenPiece;
		Player activatedPlayer = blackPlayer;

		while (!isCheckmate) {
			activatedPlayer = (activatedPlayer.color== Color.WHITE) ? blackPlayer : whitePlayer;
			System.out.println(activatedPlayer.color + "플레이어의 턴입니다.");
			updateGameInfo(activatedPlayer);
			
			if(isCheck(activatedPlayer)){
				isCheck=true;
				System.out.println("체크입니다.");
			}

			chosenPiece = activatedPlayer.choosePiece();
			activatedPlayer.movePiece(chosenPiece);
			
			if(isCheck&&isCheck(activatedPlayer)){
				System.out.println(activatedPlayer.color + "플레이어가 졌습니다.");
				isCheckmate = true;
			}
		}
	}

	private void updateGameInfo(Player player) {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		BoardManager boardManager = new BoardManager();

		Board.attackAblePosSet.clear();
		Board.moveAblePosSet.clear();
		
		while (pieceIter.hasNext()) {
			Position position = (Position) pieceIter.next();
			updatePosSet(player, position);
			
		}
		boardManager.printBoard();
	}
	
	private void updatePosSet(Player player, Position position) {

		Piece piece = Board.chessBoard.get(position);

		if (player.isSameTeam(position)) return;
		Board.attackAblePosSet.addAll(piece.attackAblePosList);
		Board.moveAblePosSet.addAll(piece.moveAblePosList);
	}

	private boolean isCheck(Player player) {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		
		while(pieceIter.hasNext()){
			Position isValidWhenKing = (Position) pieceIter.next();
			Piece inspectedPiece = Board.chessBoard.get(isValidWhenKing);

			if (!player.isSameTeam(isValidWhenKing))
				continue;
			if (!(inspectedPiece.unicode == "\u2654" || inspectedPiece.unicode == "\u265A")) 
				continue;
			if (Board.attackAblePosSet.contains(isValidWhenKing))
				return true;	
		}
		return false;
	}

}