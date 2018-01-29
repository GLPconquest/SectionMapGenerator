package generator;

public class InvalidSquareNumberException extends Exception{
	public InvalidSquareNumberException(int number) {
		super("The number "+number+" is not a valid square number.");
	}
}
