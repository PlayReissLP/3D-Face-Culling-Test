package net.pixx.wolfenstein.exceptions;

public class FatalExceptions extends WolfException {
	/* Objects */
	private static FatalExceptions fatal;
	
	/* Variables */
	public static final String EX_DISPLAY_MODE = "Couldn't set display mode.";
	public static final String EX_DISPLAY_CREATE = "Couldn't create display.";
	public static final String EX_KEYBOARD_CREATE = "Couldn't create keyboard.";
	public static final String EX_MOUSE_CREATE = "Couldn't create mouse.";
	
	/**
	 * Constructor
	 */
	protected FatalExceptions() {
		super.type = WolfException.EX_TYPE.FATAL.ordinal();
		super.message = message;
	}
	
	/**
	 * Methods
	 */
	/**
	 * Creates a new fatal exception.
	 * @param message The exception message to use.
	 * @return Fatal exception class.
	 */
	public static FatalExceptions create(String message) {
		if(FatalExceptions.fatal == null) {
			FatalExceptions.fatal = new FatalExceptions();	
		}
		
		FatalExceptions.fatal.message = message;
		
		return FatalExceptions.fatal;
	}
}
