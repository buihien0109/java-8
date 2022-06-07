package com.example.todothymeleaf.controller;

import com.example.todothymeleaf.model.Todo;
import com.example.todothymeleaf.request.CreateTodoRequest;
import com.example.todothymeleaf.request.UpdateTodoRequest;
import com.example.todothymeleaf.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class TodoController {
    // Inject bean
    private final TodoService todoService;

    // Lấy danh sách tất cả công việc
    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam(required = false, defaultValue = "") String status) {
        return todoService.getTodos(status);
    }

    // Lấy công việc theo id
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }

    // Tạo mới công việc
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoRequest request) {
        Todo todo = todoService.createTodo(request);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    // Cập nhật công việc
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    // Xóa công việc
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
