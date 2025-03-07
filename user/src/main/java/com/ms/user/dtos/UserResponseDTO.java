package com.ms.user.dtos;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email
) {
}
