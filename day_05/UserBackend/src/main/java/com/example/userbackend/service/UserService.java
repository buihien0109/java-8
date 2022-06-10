package com.example.userbackend.service;

import com.example.userbackend.dto.UserDto;
import com.example.userbackend.exception.BadRequestException;
import com.example.userbackend.exception.NotFoundException;
import com.example.userbackend.mapper.UserMapper;
import com.example.userbackend.model.User;
import com.example.userbackend.request.CreateUserRequest;
import com.example.userbackend.request.UpdatePasswordRequest;
import com.example.userbackend.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<User> users;

    public UserService() {
        initData();
    }

    public void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // Lấy danh sách user
    public List<UserDto> getUsers() {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Xóa user theo id
    public void deleteUser(int id) {
        if(findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        users.removeIf(user -> user.getId() == id);
    }

    // Tạo user mới
    public UserDto createUser(CreateUserRequest request) {
        // Kiểm tra email đã tồn tại hay chưa
        if(findUser(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Tạo user mới
        Random rd = new Random();

        User user = new User();
        user.setId(rd.nextInt(100 - 4 + 1) + 4); // Random id từ 4 -> 100
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    // Lấy thông tin user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = findUser(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return UserMapper.toUserDto(userOptional.get());
    }

    // Cập nhật thông tin user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findUser(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }

    // Cập nhật password
    public void updatePassword(int id, UpdatePasswordRequest request) {
        Optional<User> userOptional = findUser(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();

        // Kiểm tra oldPassword có chính xác hay không
        if(!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Password cũ không chính xác");
        }

        // Kiểm tra oldPassword = newPassword hay không
        if(request.getOldPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("Password cũ và password mới không được trùng nhau");
        }

        user.setPassword(request.getNewPassword());
    }

    // Quên mật khẩu
    public String forgotPassword(int id) {
        Optional<User> userOptional = findUser(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = userOptional.get();

        // Tạo mật khẩu mới
        Random rd = new Random();
        String password = String.valueOf(rd.nextInt(1000 - 100) + 100);
        user.setPassword(password);

        return password;
    }

    // HELPER METHOD
    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }


}
