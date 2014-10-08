package system;

public class Position {

	private PositionNum xPos;
	private PositionNum yPos;

	public Position(int xPos, int yPos) {
		this.xPos = new PositionNum(xPos);
		this.yPos = new PositionNum(yPos);

	}

	public int getxPos() {
		return xPos.getNo();
	}

	public int getyPos() {
		return yPos.getNo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xPos == null) ? 0 : xPos.hashCode());
		result = prime * result + ((yPos == null) ? 0 : yPos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (xPos == null) {
			if (other.xPos != null)
				return false;
		} else if (!xPos.equals(other.xPos))
			return false;
		if (yPos == null) {
			if (other.yPos != null)
				return false;
		} else if (!yPos.equals(other.yPos))
			return false;
		return true;
	}
}