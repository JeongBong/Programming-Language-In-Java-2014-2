package unit;

import system.Board;
import system.Position;

abstract public class Unit {
	static final int INPUT_MISTAKE = -1;

	public Color color;

	public enum Color{
		 WHITE(1), BLACK(-1);	
		 
		 private int no;
		 private Color(int no){
			 this.no = no;
		 }
		 
		 public int getNo(){
			 return no;
		 }
	}	
		
	public boolean isSameTeam(Position position) {
		return this.color.getNo() == Board.chessBoard.get(position).color.getNo();
	}

	boolean isEmptyPlace(Position position) {
		return !Board.chessBoard.containsKey(position);
	}
	
	//INPUT_MISTAKE인 경우는 보드의 범위를 벗어나는 경우
	boolean isValidPosition(Position position) {
		if ((position.getxPos() != INPUT_MISTAKE) && (position.getyPos() != INPUT_MISTAKE))
			return true;
		return false;
	}


}