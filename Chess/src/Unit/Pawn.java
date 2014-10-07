package unit;

import system.Board;
import system.Position;

public class Pawn extends Piece {
	int direction;

	public Pawn(Color color) {
		super(color);
		direction = color.getNo();
		unicodeForPrint = ((this.color == Color.WHITE) ? "\u2659" : "\u265F");
	}

	@Override
	public void resetMoveablePositionList(Position position_base) {
		moveAblePositionList.clear();
		addAccessiblePosition(position_base);
		addAttackablePosition(position_base);
	}

	private void checkPawnPath(int xScale, int yScale, Position basePosition) {
		int xPos = basePosition.getxPos() + xScale;
		int yPos = basePosition.getyPos();
		Position beside = new Position(xPos, yPos); //앙파상일 경우 공격가능한 폰의 위치
		Position inspectedPos = new Position(xPos, yPos+direction);
		
		
		if (xScale != 0) {//공격하는 경우 움직이는 경로
		
			if (isValidPosition(inspectedPos) && !isEmptyPlace(inspectedPos)
					&& !isSameTeam(inspectedPos)) {
				moveAblePositionList.add(inspectedPos);
			}
			if(isEnpassant(inspectedPos, beside)){
				moveAblePositionList.add(inspectedPos);
			}
				
			return;
		}

		for (int i = 0; i < yScale * direction; i++) {
			inspectedPos = new Position(xPos, yPos+yScale);		
		
			if (!isAddable(inspectedPos)) break;
			moveAblePositionList.add(inspectedPos);
			if (!isEmptyPlace(inspectedPos)) break;
		}
	}
	
	private boolean isEnpassant(Position inspectedPos, Position beside) {
		
		if(!(isValidPosition(inspectedPos)||isEmptyPlace(inspectedPos))) return false;
		if(!isEmptyPlace(beside)&&!isSameTeam(beside)&&Board.chessBoard.get(beside).moveCount==1) return true;
	
		return false;
	}

	//방향에 따라 체크하는 위치가 달라진다.
	void addAccessiblePosition(Position position_base) {
		checkPawnPath(-1, 1 * direction,position_base);
		checkPawnPath(1, 1 * direction,position_base);

		if (moveCount == 0) {//처음 움직일 경우에만 2칸 움직인다.
			checkPawnPath(0, 2 * direction,position_base);
			return;
		}
		checkPawnPath(0, 1 * direction,position_base);
	}
	
	//폰은 공격할 수 있는 경로와 움직일 수 있는 경로를 따로 저장한다. 폰을 제외한 말들은 두 경로를 같은 알고리즘으로 저장.	

	private void checkPawnAttackPath(int xScale, int yScale, Position position_base) {
		int newxPos = position_base.getxPos() + xScale;
		int newyPos = position_base.getyPos() + yScale;
		Position newPosition;

		newPosition = new Position(newxPos, newyPos);

		if (isValidPosition(newPosition)) {
			attackAblePositionList.add(newPosition);
		}
	}
	
	private void addAttackablePosition(Position position_base) {
		checkPawnAttackPath(-1, 1 * direction,position_base);
		checkPawnAttackPath(1, 1 * direction,position_base);
	}


	@Override
	protected boolean isAddable(Position newPosition) {
		if (!isValidPosition(newPosition))
			return false;
		if (!isEmptyPlace(newPosition))
			return false;

		return true;
	}

}