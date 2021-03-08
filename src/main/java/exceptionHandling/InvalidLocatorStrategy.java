package exceptionHandling;

public class InvalidLocatorStrategy extends RuntimeException{
	
	public InvalidLocatorStrategy(String message) {
		super(message);
	}
}
