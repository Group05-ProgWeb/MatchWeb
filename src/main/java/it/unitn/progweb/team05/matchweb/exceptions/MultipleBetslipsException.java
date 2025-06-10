package it.unitn.progweb.team05.matchweb.exceptions;

public class MultipleBetslipsException extends Exception {
    public MultipleBetslipsException() {
        super("Only one betslip per day is allowed.");
    }
}
