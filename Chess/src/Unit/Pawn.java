package unit;

import system.Board;
import system.Position;

public class Pawn extends Piece {
	int direction;

	public Pawn(Color color) {
		super(color);
		direction = color.getNo();
		unicode = ((this.color == Color.WHITE) ? "\u2659" : "\u265F");
	}

	@Override
	public void resetMoveablePosList(Position basePos) {
		moveAblePosList.clear();
		addAccessiblePos(basePos);
		addAttackablePosition(basePos);
	}

	private void checkPawnPath(int xScale, int yScale, Position basePos) {
		int xPos = basePos.getxPos() + xScale;
		int yPos = basePos.getyPos();
		Position beside = new Position(xPos, yPos); //앙파상일 경우 공격가능한 폰의 위치
		Position checkedPos = new Position(xPos, yPos+direction);
		
		
		if (xScale != 0) {//공격하는 경우 움직이는 경로
		
			if (isValidPos(checkedPos) && !isEmptyPlace(checkedPos)
					&& !isSameTeam(checkedPos)) {
				moveAblePosList.add(checkedPos);
			}
			if(isEnpassant(checkedPos, beside)){
				moveAblePosList.add(checkedPos);
			}
				
			return;
		}

		for (int i = 0; i < yScale * direction; i++) {
			checkedPos = new Position(xPos, yPos+yScale);		
		
			if (!isAddable(checkedPos)) break;
			moveAblePosList.add(checkedPos);
			if (!isEmptyPlace(checkedPos)) break;
		}
	}
	
	private boolean isEnpassant(Position checkedPos, Position beside) {
		
		if(!(isValidPos(checkedPos)||isEmptyPlace(checkedPos))) return false;
		if(!isEmptyPlace(beside)&&!isSameTeam(beside)&&Board.chessBoard.get(beside).moveCount==1) return true;
	
		return false;
	}

	//방향에 따라 체크하는 위치가 달라진다.
	void addAccessiblePos(Position basePos) {
		checkPawnPath(-1, 1 * direction,basePos);
		checkPawnPath(1, 1 * direction,basePos);

		if (moveCount == 0) {//처음 움직일 경우에만 2칸 움직인다.
			checkPawnPath(0, 2 * direction,basePos);
		}
		checkPawnPath(0, 1 * direction,basePos);
	}
	
	//폰은 공격할 수 있는 경로와 움직일 수 있는 경로를 따로 저장한다. 폰을 제외한 말들은 두 경로를 같은 알고리즘으로 저장.	

	private void checkPawnAttackPath(int xScale, int yScale, Position basePos) {
		int xPos = basePos.getxPos() + xScale;
		int yPos = basePos.getyPos() + yScale;
		Position checkedPos;

		checkedPos = new Position(xPos, yPos);

		if (isValidPos(checkedPos)) {
			attackAblePosList.add(checkedPos);
		}
	}
	
	private void addAttackablePosition(Position basePos) {
		checkPawnAttackPath(-1, 1 * direction,basePos);
		checkPawnAttackPath(1, 1 * direction,basePos);
	}


	@Override
	protected boolean isAddable(Position pos) {
		if (!isValidPos(pos))
			return false;
		if (!isEmptyPlace(pos))
			return false;

		return true;
	}

}