package com.example.todo.exception;

public class TodoEndDateIsNotValidException extends TodoRequestException {
    public TodoEndDateIsNotValidException() {
        super("The provided endDate format is not valid for the todo.");
    }
}
