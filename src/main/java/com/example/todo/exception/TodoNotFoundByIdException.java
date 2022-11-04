package com.example.todo.exception;

public class TodoNotFoundByIdException extends TodoRequestException {
    public TodoNotFoundByIdException() {
        super("The todo is not found by the given id.");
    }
}
