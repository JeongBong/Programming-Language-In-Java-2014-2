package system;

public class PositionNum {

	final static int MAX_POSITION = 7;
	final static int MIN_POSITION = 0;
	public final static int INPUT_MISTAKE = -1;
	private int positionNum;

	PositionNum(int inputNum) {
		if (inputNum > MAX_POSITION || inputNum < MIN_POSITION) {
			positionNum = INPUT_MISTAKE;
		} else {
			positionNum = inputNum;
		}
	}

	int getNo() {
		return positionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionNum;
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
		PositionNum other = (PositionNum) obj;
		if (positionNum != other.positionNum)
			return false;
		return true;
	}

}