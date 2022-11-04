package com.example.todo.exception;

public class TodoJsonUpdatingException extends TodoInternalException {
    public TodoJsonUpdatingException() {
        super("An error occurred while trying to update the exact todo in the todos json file.");
    }
}
