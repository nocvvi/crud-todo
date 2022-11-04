package com.example.todo.exception;

public class TodoJsonWritingException extends TodoInternalException {
    public TodoJsonWritingException() {
        super("An error occurred while trying to write the data into the todos json file.");
    }
}
