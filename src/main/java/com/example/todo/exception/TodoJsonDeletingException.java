package com.example.todo.exception;

public class TodoJsonDeletingException extends TodoInternalException {
    public TodoJsonDeletingException() {
        super("An error occurred while trying to delete the exact todo in the todos json file.");
    }
}
