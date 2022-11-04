package com.example.todo.controller;

import com.example.todo.exception.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService = new TodoService();

    @GetMapping("/get")
    public ResponseEntity<Object> getTodos() {
        try {
            return ResponseEntity.ok(todoService.getAll());
        } catch (TodoInternalException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        try {
            return ResponseEntity.ok(todoService.create(todo));
        } catch (TodoInternalException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (TodoRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable UUID id, @RequestBody Todo todoData) {
        try {
            return ResponseEntity.ok(todoService.update(id, todoData));
        } catch (TodoInternalException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (TodoRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable UUID id) {
        try {
            boolean isDeleted = todoService.delete(id);
            if (isDeleted) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(409).body(false);
        } catch (TodoInternalException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
