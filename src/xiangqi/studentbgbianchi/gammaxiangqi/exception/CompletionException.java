package xiangqi.studentbgbianchi.gammaxiangqi.exception;

public class CompletionException extends RuntimeException{
	String message;
	public CompletionException(String m)
	{
		this.message = m;
	}
}
