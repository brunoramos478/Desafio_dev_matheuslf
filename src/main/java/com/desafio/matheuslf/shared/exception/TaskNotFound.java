package com.desafio.matheuslf.shared.exception;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound() {
        super("Tarefa não encontrada");
    }

    public TaskNotFound(String message) {
        super(message);
    }
}
