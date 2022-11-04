package com.example.todo.exception;

public class TodoJsonReadingException extends TodoInternalException {
    public TodoJsonReadingException() {
        super("An error occurred while trying to read the data out of the todos json file.");
    }
}
