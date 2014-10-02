package Unit;

import System.*;

public class Player extends Unit {
	public boolean check;
	public Player(Identity id) {
		this.id = id;
		check =false;
	}

	public Piece choosePiece() {
		Position position = inputPosition();
		Piece chosenPiece;	
		
		while(!Chess.Board.containsKey(position)){
			System.out.println("선택한 위치에 체스말이 없습니다. 다시 선택하세요.");
			position = inputPosition();
		}
		Chess.Board.get(position).resetPath();
		
		while (!isSameTeam(position)||Chess.Board.get(position).moveAblePosition.isEmpty()) {
			System.out.println("움직일 수 있는 체스말이 아닙니다. 다시 선택하세요.");
			position = inputPosition();
		}
		chosenPiece = Chess.Board.get(position);
		
		return chosenPiece;
	}
}