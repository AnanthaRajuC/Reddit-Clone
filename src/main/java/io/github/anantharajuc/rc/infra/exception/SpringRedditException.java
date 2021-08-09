package io.github.anantharajuc.rc.infra.exception;

public class SpringRedditException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public SpringRedditException(String exMessage, Exception exception) 
    {
        super(exMessage, exception);
    }

    public SpringRedditException(String exMessage) 
    {
        super(exMessage);
    }
}
