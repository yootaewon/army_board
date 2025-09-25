package com.army.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SignInDTO {
    private final String armyNumber;
    private final String password;
}
