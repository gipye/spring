package com.example.spring.dto;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDto<T> {
    HttpStatus status;
    T data;
}
