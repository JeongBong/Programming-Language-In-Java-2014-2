package system;

public class PositionNum {

	final static int MAX_POSITION = 7;
	final static int MIN_POSITION = 0;
	public final static int INPUT_MISTAKE = -1;
	private int positionNumber;

	PositionNum(int inputNumber) {
		if (inputNumber > MAX_POSITION || inputNumber < MIN_POSITION) {
			positionNumber = INPUT_MISTAKE;
		} else {
			positionNumber = inputNumber;
		}
	}

	int getNo() {
		return positionNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionNumber;
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
		if (positionNumber != other.positionNumber)
			return false;
		return true;
	}

}