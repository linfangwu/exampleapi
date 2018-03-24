package se.example.api.apiserver.exeption;


public class WrongAnswerException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongAnswerException(){
        super();
    }

    public WrongAnswerException(String str){
        super(str);
    }

    public WrongAnswerException(Throwable t){
        super(t);
    }
}
