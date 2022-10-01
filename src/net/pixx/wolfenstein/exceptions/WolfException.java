package net.pixx.wolfenstein.exceptions;

public class WolfException {
	/* Enums */
	protected enum EX_TYPE {
		FATAL
	}
	
	/* Variables */
	private static final String FATAL_STR = "[Wolfenstein-FATAL]: ";
	protected String message;
	protected int type;
	
	/**
	 * Methods
	 */
	/**
	 * Prints the exception as an error to the console.
	 * @param ex The exception to get the message from.
	 */
	public static void createMessage(WolfException ex) {
		String ex_msg = "";
		
		if(!WolfException.isStringNullOrEmpty(ex.message)) {
			switch(ex.type) {
				case 0: // FATAL
					ex_msg += WolfException.FATAL_STR;
					break;
			}
			
			if(!WolfException.isStringNullOrEmpty(ex_msg)) {
				ex_msg += ex.message;
				System.err.println(ex_msg);
			}
		}
	}
	
	/**
	 * Protected Methods
	 */
	/**
	 * Checks if a string is null or empty
	 * @param s The string to check.
	 * @return If the string is null or empty.
	 */
	protected static final boolean isStringNullOrEmpty(String s) {
		boolean b = false;
		
		if(s == null) {
			b = true;
		}
		if(s == "") {
			b = true;
		}
		
		return b;
	}
}
