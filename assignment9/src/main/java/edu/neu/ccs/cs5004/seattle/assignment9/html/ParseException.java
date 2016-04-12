package edu.neu.ccs.cs5004.seattle.assignment9.html;

/**
 * Represents the exception to be thrown when an incorrectly formatted element
 * is being parsed
 *
 * @author yoganandc alesyavt
 */
public class ParseException extends RuntimeException {

    public ParseException(String msg) {
        super(msg);
    }

}
