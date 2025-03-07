package com.ms.email.dtos;

import java.util.UUID;

public record EmailRecordDTO(
        UUID id,
        String emailTo,
        String subject,
        String text
) {
}
