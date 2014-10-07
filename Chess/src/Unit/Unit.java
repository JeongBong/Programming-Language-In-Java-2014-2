package unit;

import system.Board;
import system.Position;

abstract public class Unit {

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
}