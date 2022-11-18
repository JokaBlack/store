package com.example.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    @NotBlank
    private String email;
    @NotBlank
    private String nickName;
    @NotBlank
    private String password;
}
