package com.example.todo.service;

import com.example.todo.exception.*;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository = new TodoRepository();

    public List<Todo> getAll() throws TodoJsonReadingException {
        return todoRepository.read();
    }

    public Todo create(Todo todo) throws TodoNameIsEmptyException, TodoEndDateIsNotValidException, TodoIsCompleteIsNotSetException, TodoJsonWritingException {
        try {
            todo.setId();
            todo.setName(todo.getName());
            todo.setEndDate(todo.getEndDate());
            todo.setIsComplete(todo.getIsComplete());
            List<Todo> todos = todoRepository.read();
            todos.add(todo);
            todoRepository.write(todos);
            return todo;
        } catch (TodoJsonReadingException e) {
            throw new TodoJsonWritingException();
        }
    }

    public Todo update(UUID id, Todo todoData) throws TodoNameIsEmptyException, TodoNotFoundByIdException, TodoEndDateIsNotValidException, TodoIsCompleteIsNotSetException, TodoJsonUpdatingException {
        try {
            List<Todo> todos = todoRepository.read();
            Todo todo = todos.stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow(TodoNotFoundByIdException::new);
            todos.remove(todo);
            if (todoData.getName() != null && !todoData.getName().isEmpty()) {
                todo.setName(todoData.getName());
            }
            if (todoData.getEndDate() != null && !todoData.getEndDate().isEmpty()) {
                todo.setEndDate(todoData.getEndDate());
            }
            if (todoData.getIsComplete() != null) {
                todo.setIsComplete(todoData.getIsComplete());
            }
            todos.add(todo);
            todoRepository.write(todos);
            return todo;
        } catch (TodoJsonWritingException|TodoJsonReadingException e) {
            throw new TodoJsonUpdatingException();
        }
    }

    public Boolean delete(UUID id) throws TodoJsonDeletingException {
        try {
            if (todoRepository.read().stream().noneMatch((todo) -> todo.getId().equals(id))) {
                return false;
            }
            List<Todo> todos = todoRepository.read().stream().filter((todo) -> !todo.getId().equals(id)).toList();
            todoRepository.write(todos);
            return true;
        } catch (TodoJsonWritingException|TodoJsonReadingException e) {
            throw new TodoJsonDeletingException();
        }
    }
}
