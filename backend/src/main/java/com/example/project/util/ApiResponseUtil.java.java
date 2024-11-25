package com.example.project.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseUtil {

    /**
     * 生成成功的响应
     *
     * @param data 响应数据
     * @return ResponseEntity 包装的响应
     */
    public static ResponseEntity<Object> success(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 生成成功的响应，自定义消息
     *
     * @param data 响应数据
     * @param message 自定义消息
     * @return ResponseEntity 包装的响应
     */
    public static ResponseEntity<Object> success(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 生成错误的响应
     *
     * @param message 错误消息
     * @return ResponseEntity 包装的响应
     */
    public static ResponseEntity<Object> error(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 生成错误的响应，自定义状态码
     *
     * @param message 错误消息
     * @param status HTTP 状态码
     * @return ResponseEntity 包装的响应
     */
    public static ResponseEntity<Object> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}
