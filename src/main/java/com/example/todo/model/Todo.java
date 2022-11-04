package com.example.todo.model;


import com.example.todo.exception.TodoEndDateIsNotValidException;
import com.example.todo.exception.TodoIsCompleteIsNotSetException;
import com.example.todo.exception.TodoNameIsEmptyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Todo {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private UUID id;
    private String name;
    private String endDate;
    private Boolean isComplete;

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TodoNameIsEmptyException {
        if (name == null || name.isEmpty()) {
            throw new TodoNameIsEmptyException();
        }
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws TodoEndDateIsNotValidException {
        if (endDate == null || endDate.isEmpty()) {
            throw new TodoEndDateIsNotValidException();
        }
        try {
            this.endDate = dateFormat.format(dateFormat.parse(endDate));
        } catch (ParseException e) {
            throw new TodoEndDateIsNotValidException();
        }
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) throws TodoIsCompleteIsNotSetException {
        if (isComplete == null) {
            throw new TodoIsCompleteIsNotSetException();
        }
        this.isComplete = isComplete;
    }
}
