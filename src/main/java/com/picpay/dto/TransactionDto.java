package com.picpay.dto;

import java.math.BigDecimal;

public record TransactionDto(
    BigDecimal amount,
    Long senderId,
    Long receiverId
) {
}
