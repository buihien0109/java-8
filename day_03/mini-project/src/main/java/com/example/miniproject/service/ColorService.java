package com.example.miniproject.service;

import com.example.miniproject.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("type = " + type + " không hợp lệ");
        };
    }

    public String randomColorName() {
        String[] colors = {"red", "green", "blue", "yellow", "black"};
        Random rd = new Random();
        return colors[rd.nextInt(colors.length)];
    }

    public String randomHexColor() {
        Random rd = new Random();
        String[] characters = {"a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder sb = new StringBuilder("#");

        for (int i = 0; i < 6; i++) {
            String character = characters[rd.nextInt(characters.length)];
            sb.append(character);
        }

        return sb.toString();
    }

    public String randomRgbColor() {
        Random rd = new Random();
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);

        return "rgb(" + r + "," + g + "," + b + ")";
    }
}
