package System;

import java.util.Iterator;

import Unit.*;
import Unit.Unit.Color;

public class Manager {

	Player player_white;
	Player player_black;
	boolean isGameContinue;

	Manager() {
		player_white = new Player(Color.WHITE);
		player_black = new Player(Color.BLACK);
		isGameContinue = true;
	}

	void startChess() {
		Piece piece_chosen;
		Player player_activated = player_black;

		while (isGameContinue) {
			player_activated = (player_activated.color.getNo() == 1) ? player_black : player_white;
			updateGameInfo(player_activated);
			
			if (isCheck(player_activated)) {
				isGameContinue = judgeWhetherContinue(player_activated);
			}
			piece_chosen = player_activated.choosePiece();
			player_activated.movePiece(piece_chosen);
		}
	}

	private void updateGameInfo(Player player) {
		Iterator<Position> boardIter = Board.chessBoard.keySet().iterator();
		Board.attackAblePositionSet.clear();

		while (boardIter.hasNext()) {
			Position key_position = (Position) boardIter.next();
			updateAttackAblePosition(player, key_position);
		}
		printBoard();
		
	}
	
	private void updateAttackAblePosition(Player player, Position position) {
		Piece newPiece = Board.chessBoard.get(position);

		if (player.isSameTeam(position))
			return;
		Board.attackAblePositionSet.addAll(newPiece.attackAblePositionList);
	}
	
	private boolean judgeWhetherContinue(Player player_activated) {
		Piece piece_chosen;

		piece_chosen = player_activated.choosePiece();
		player_activated.movePiece(piece_chosen);
		
		updateGameInfo(player_activated);
			
		if (isCheck(player_activated)) {
			System.out.println(player_activated.color.getNo() + "이 졌습니다.");
				return false;
		}
		
		return true;
	}


	void printBoard(){
		
	for(int yPos=0;yPos<8;yPos++){
		for(int xPos=0;xPos<8;xPos++){
		Position key_forPrint = new Position(xPos, yPos);
		if(Board.chessBoard.containsKey(key_forPrint)){
			System.out.print(Board.chessBoard.get(key_forPrint).unicodeForPrint);
		}else{
			System.out.print("ㅁ");
		}
		}
		System.out.println();
	}
	}	


	private boolean isCheck(Player player) {
		boolean isCheck = false;
		Iterator<Position> boardIter = Board.chessBoard.keySet().iterator();

		while (boardIter.hasNext()) {
			Position key_position = (Position) boardIter.next();
			isCheck = judgeCheck(player, key_position);
			if (isCheck) return isCheck;
		}
		return isCheck;
	}
	
//	HACK: 아름답지 못하다.isCheck 고칠것. isNotCheck?
	private boolean judgeCheck(Player player, Position position) {
		boolean isCheck = false;
		Piece piece_gonnaInspect = Board.chessBoard.get(position);

		if (!player.isSameTeam(position))
			return isCheck;
		if (piece_gonnaInspect.unicodeForPrint != "\u2654" && piece_gonnaInspect.unicodeForPrint != "\u265A") //HACK: 유니코드는 여기 쓰는게 아니에요.
			return isCheck;
		if (Board.attackAblePositionSet.contains(position)) {
			if (player.check == true)
				return isCheck == true;
			isCheck = piece_gonnaInspect.moveAblePositionList.isEmpty();
			player.check = isCheck;
			System.out.println("체크입니다. 심사숙고하세요.");
		}
		return isCheck;
	}

}