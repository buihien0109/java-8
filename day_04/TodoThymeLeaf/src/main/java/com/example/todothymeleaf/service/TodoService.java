package com.example.todothymeleaf.service;

import com.example.todothymeleaf.exception.NotFoundException;
import com.example.todothymeleaf.model.Todo;
import com.example.todothymeleaf.request.CreateTodoRequest;
import com.example.todothymeleaf.request.UpdateTodoRequest;
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

    // Trong constructor, tạo ra 1 số công việc và thêm vào danh sách
    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();

        todos.add(new Todo(rd.nextInt(100), "Làm bài tập", true));
        todos.add(new Todo(rd.nextInt(100), "Đi đá bóng", false));
        todos.add(new Todo(rd.nextInt(100), "Đi chơi với bạn bè", true));
    }

    // Lấy danh sách công việc theo trạng thái
    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    // Lấy thông tin công việc theo id
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

    // Tạo công việc mới
    public Todo createTodo(CreateTodoRequest request) {
        // Có thể validate title nếu để trống (Tạo thêm BadRequestException)

        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100), request.getTitle(), false);
        todos.add(todo);

        return todo;
    }

    // Cập nhật công việc
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

    // Xóa công việc
    public void deleteTodo(int id) {
        Todo todo = getTodoById(id);
        todos.removeIf(t -> t.getId() == todo.getId());
    }

    // Helper method : Tìm kiếm 1 công việc theo id
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }
}
