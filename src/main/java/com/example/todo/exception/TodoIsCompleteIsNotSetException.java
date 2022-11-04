package com.example.todo.exception;

public class TodoIsCompleteIsNotSetException extends TodoRequestException {
    public TodoIsCompleteIsNotSetException() {
        super("The todo's isComplete field must be either true, or false.");
    }
}
