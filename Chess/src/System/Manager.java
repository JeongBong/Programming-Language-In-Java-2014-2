package System;
import java.util.Iterator;

import Unit.*;
import Unit.Unit.Identity;

public class Manager {
	final static int MAXWIDTH = 8;
	final static int MAXHEIGHT = 8;
	Player player1;
	Player player2;
	boolean result;
	
	Manager() {
		player1 = new Player(Identity.WHITE_PLAYER);
		player2 = new Player(Identity.BLACK_PLAYER);
		result=false;
	}

	int playChess() {
		while (true) {
			System.out.println("플레이어1 차례입니다.");
			result = playTurn(player1);
			if(!result) return 0;
			System.out.println("플레이어2 차례입니다.");
			result = playTurn(player2);
			if(!result) return 1;
	 }
	}
	
	private boolean playTurn(Player player) {
		Piece newPiece;
		readyNextTurn(player);

		if (isCheckMate(player)) {
			System.out.println("움직일 체스말의 좌표값을 입력하세요.");
			newPiece = player.choosePiece();
			newPiece.move();
			readyNextTurn(player);
			if(isCheckMate(player)){
				System.out.println(player.id+"이 졌습니다.");
			}
			return false;
		}
		System.out.println("움직일 체스말의 좌표값을 입력하세요.");
		newPiece = player.choosePiece();
		System.out.println("움직일 위치 좌표값을 입력하세요.");
		newPiece.move();
		return true;
	}

	private void readyNextTurn(Player player) {
		String chessBoard[][] = new String[MAXWIDTH][MAXHEIGHT];
		Iterator<Position> iterator = Chess.Board.keySet().iterator();
		Chess.attackAblePositionSet.clear();
		
		while (iterator.hasNext()) {
			Position key = (Position) iterator.next();
			updateBoard(chessBoard, key);
			updateAttackAblePosition(player, key);
		}	
		printBoard(chessBoard);
	}
	
	private void updateBoard(String[][] chessBoard, Position position) {
		Piece newPiece = Chess.Board.get(position);
		chessBoard[position.getxPos()][position.getyPos()] = newPiece.unicode;
	
		for (int i = 0; i < MAXHEIGHT; i++) {
			for (int j = 0; j < MAXWIDTH; j++) {
				if(chessBoard[j][i]==null){
					chessBoard[j][i]="ㅁ";
				}
			}
		}
	}
	
	private void updateAttackAblePosition(Player player, Position position) {
		Piece newPiece = Chess.Board.get(position);
		
		if(player.isSameTeam(position)) return;		
		Chess.attackAblePositionSet.addAll(newPiece.attackAblePosition);
	}
	
	private void printBoard(String[][] chessBoard) {
		IconPrinter printer = new IconPrinter();
		for (int i = 0; i < MAXHEIGHT; i++) {
			for (int j = 0; j < MAXWIDTH; j++) {
					System.out.print(" " + chessBoard[j][i] + " ");
				}
			System.out.println();
		}
	}
	
	private boolean isCheckMate(Player player){
		boolean isVictory = false;
		Iterator<Position> iterator = Chess.Board.keySet().iterator();

		while (iterator.hasNext()) {
			Position key = (Position) iterator.next();
			isVictory = judgeCheckMate(player, key);
			if(isVictory) return isVictory;
		}
		return isVictory;
	}
	
	private boolean judgeCheckMate(Player player, Position position) {
		boolean isCheckMate = false;
		Piece piece = Chess.Board.get(position);
		
		if (!player.isSameTeam(position)) return false;
		if (piece.id.getNo() != 2 && piece.id.getNo() != 3)  return false;
		if (Chess.attackAblePositionSet.contains(position)){
			if(player.check==true) return true;
			isCheckMate = piece.moveAblePosition.isEmpty();
			player.check=isCheckMate;
			System.out.println("체크입니다. 심사숙고하세요.");
		}
		return isCheckMate;
	}
	
}