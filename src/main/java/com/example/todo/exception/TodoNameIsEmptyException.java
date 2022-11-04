package com.example.todo.exception;

public class TodoNameIsEmptyException extends TodoRequestException {
    public TodoNameIsEmptyException() {
        super("The todo must have non-empty name.");
    }
}
