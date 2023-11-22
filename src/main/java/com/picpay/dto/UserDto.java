package com.picpay.dto;

import com.picpay.domain.UserType;

import java.math.BigDecimal;

public record UserDto(
        String name,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance
) {
}
