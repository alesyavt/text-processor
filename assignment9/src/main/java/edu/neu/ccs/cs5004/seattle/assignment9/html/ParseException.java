package edu.neu.ccs.cs5004.seattle.assignment9.html;

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
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
