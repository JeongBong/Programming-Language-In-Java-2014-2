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
			// YG: 상대편 플레이어의 정보를 가져다 써야 한다면
			// YG: 상대편 플레이어를 저장해두고,
			// YG: 여기서 서로 교체하는 방식도 고려해볼만.
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

		// YG: piece.color == player.color가 더 명확하지 않을까?
		if (player.isSameTeam(position)) return;
		Board.attackAblePosSet.addAll(piece.attackAblePosList);
		// YG: moveAblePosSet은 여기에서 설정해주지만 King에서만 쓰이는데 board에 있을 필요가 있을까?
		// YG: 90번째줄 주석 참고
		Board.moveAblePosSet.addAll(piece.moveAblePosList);
	}

	private boolean isCheck(Player player) {
		Iterator<Position> pieceIter = Board.chessBoard.keySet().iterator();
		
		while(pieceIter.hasNext()){
			// YG: 이 변수 무슨 역할인지 이해할 수 없음
			Position isValidWhenKing = (Position) pieceIter.next();
			Piece inspectedPiece = Board.chessBoard.get(isValidWhenKing);

			if (!player.isSameTeam(isValidWhenKing))
				continue;
			if (!(inspectedPiece.unicode == "\u2654" || inspectedPiece.unicode == "\u265A")) 
				continue;
			
			// YG: attackablePosSet이 여기랑 Player에만 쓰이는데 board에 있을 필요가 있을까?
			// YG: player가 들고 있어도 되는거 아닐까?
			// YG: 내가 공격할 수 있는 위치인지, 공격 당할 수 있는 위치인지 매우 햇갈림.
			if (Board.attackAblePosSet.contains(isValidWhenKing))
				return true;	
		}
		return false;
	}

}