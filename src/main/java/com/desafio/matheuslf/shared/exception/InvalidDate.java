package com.desafio.matheuslf.shared.exception;

public class InvalidDate extends RuntimeException {

    public InvalidDate() {
        super("A data de término deve ser posterior à data de início.");
    }
    public InvalidDate(String message) {
        super(message);
    }
}
