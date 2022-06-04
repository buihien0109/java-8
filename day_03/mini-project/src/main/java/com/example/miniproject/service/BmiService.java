package com.example.miniproject.service;

import com.example.miniproject.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    public double calculateBmi(double height, double weight) {
        // Kiểm tra nếu height hoặc weight <= 0 => throw BadRequestException
        if (height <= 0 || weight <= 0) {
            throw new BadRequestException("Chiều cao hoặc cân năng phải > 0");
        }

        // Ngược lại thì tính toán theo công thức và trả về kết quả
        return weight / (height * height);
    }
}
