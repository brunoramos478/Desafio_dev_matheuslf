package com.desafio.matheuslf.adapter.in.handler;

import com.desafio.matheuslf.shared.exception.InvalidDate;
import com.desafio.matheuslf.shared.exception.ProjectNotFound;
import com.desafio.matheuslf.shared.exception.TaskNotFound;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDate.class)
    public ProblemDetail handleInvalidDate(InvalidDate e) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );

        problemDetail.setTitle("Invalid Date");
        return problemDetail;

    }

    @ExceptionHandler(ProjectNotFound.class)
    public ProblemDetail handleProjectNotFound(ProjectNotFound e) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        problemDetail.setTitle("Project Not Found");
        return problemDetail;
    }

    @ExceptionHandler(TaskNotFound.class)
    public ProblemDetail handleTaskNotFound(TaskNotFound e) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        problemDetail.setTitle("Task Not Found");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos estão inválidos. Verifique e tente novamente."
        );

        problemDetail.setTitle("Requisição Inválida");

        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                map.put(error.getField(), error.getDefaultMessage())
        );

        problemDetail.setProperty("Erros", map);
        return problemDetail;
    }
}
