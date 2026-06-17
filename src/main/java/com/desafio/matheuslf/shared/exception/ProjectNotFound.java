package com.desafio.matheuslf.shared.exception;

public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound() {
        super("Projeto não encontrado");
    }

    public ProjectNotFound(String message) {
        super(message);
    }
}
