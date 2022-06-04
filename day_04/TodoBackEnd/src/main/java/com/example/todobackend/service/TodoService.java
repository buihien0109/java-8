package com.example.todobackend.service;

import com.example.todobackend.exception.NotFoundException;
import com.example.todobackend.model.Todo;
import com.example.todobackend.request.CreateTodoRequest;
import com.example.todobackend.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    // Tạo ra danh sách todos để quản lý
    private List<Todo> todos;

    // Trong constructor, tạo ra 1 số đối tượng todo và thêm vào danh sách
    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();

        todos.add(new Todo(rd.nextInt(100), "Làm bài tập", true));
        todos.add(new Todo(rd.nextInt(100), "Đi đá bóng", false));
        todos.add(new Todo(rd.nextInt(100), "Đi chơi với bạn bè", true));
    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    public Todo getTodoById(int id) {
        Optional<Todo> todoOptional = findById(id);
//        if(todoOptional.isPresent()) {
//            return todoOptional.get();
//        }
//
//        throw new NotFoundException("Không tìm thấy công việc có id = " + id);

        return todoOptional.orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + id);
        });
    }

    public Todo createTodo(CreateTodoRequest request) {
        // Có thể validate title nếu để trống (Tạo thêm BadRequestException)

        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100), request.getTitle(), false);
        todos.add(todo);

        return todo;
    }

    public Todo updateTodo(int id, UpdateTodoRequest request) {
        // Kiểm tra xem công việc có tồn tại hay không
//        Optional<Todo> todoOptional = findById(id);
//        if (todoOptional.isEmpty()) {
//            throw new NotFoundException("Không tìm thấy công việc có id = " + id);
//        }

        // Cập nhật công việc theo thông tin từ request
        Todo todo = getTodoById(id);
        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        // Trả về công việc sau khi cập nhật thành công
        return todo;
    }

    public void deleteTodo(int id) {
        Todo todo = getTodoById(id);
        todos.removeIf(t -> t.getId() == todo.getId());
    }

    // Helper method : Tìm kiếm 1 todo theo id
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }
}
