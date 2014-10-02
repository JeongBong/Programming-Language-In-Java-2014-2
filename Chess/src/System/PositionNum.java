package System;

public class PositionNum {

	final static int MAXPOSITION = 7;
	final static int MINPOSITION = 0;
	final static int FAIL = -1;
	private int positionNumber;

	PositionNum(int integer) {
		if (integer > MAXPOSITION || integer < MINPOSITION) {
			positionNumber = FAIL;
		} else {
			positionNumber = integer;
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