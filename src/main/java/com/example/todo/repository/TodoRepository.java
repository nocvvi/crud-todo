package com.example.todo.repository;

import com.example.todo.exception.TodoJsonReadingException;
import com.example.todo.exception.TodoJsonWritingException;
import com.example.todo.model.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Repository
public class TodoRepository {
    private static final String TODOS_PATH = "todos.txt";
    private static final Type TODOS_TYPE = new TypeToken<List<Todo>>() {
    }.getType();

    public TodoRepository() {
        TodoRepository.initializeFile();
    }

    private static void initializeFile() {
        try {
            Path todosPath = Path.of(TodoRepository.TODOS_PATH);
            if (!Files.exists(todosPath)) {
                Files.createFile(todosPath);
            }
            FileReader fileReader = new FileReader(TodoRepository.TODOS_PATH);
            Scanner scanner = new Scanner(fileReader);
            boolean hasNext = scanner.hasNext();
            scanner.close();
            fileReader.close();
            if (hasNext) {
                return;
            }
            FileWriter fileWriter = new FileWriter(TodoRepository.TODOS_PATH);
            fileWriter.write("[]");
            fileWriter.flush();
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public List<Todo> read() throws TodoJsonReadingException {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(TodoRepository.TODOS_PATH));
            return gson.fromJson(reader, TodoRepository.TODOS_TYPE);
        } catch (Exception e) {
            throw new TodoJsonReadingException();
        }
    }

    public void write(List<Todo> todo) throws TodoJsonWritingException {
        try (FileWriter file = new FileWriter(TodoRepository.TODOS_PATH)) {
            file.write(new Gson().toJson(todo));
            file.flush();
        } catch (Exception e) {
            throw new TodoJsonWritingException();
        }
    }
}
