package com.ms.email.repositories;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
    List<EmailModel> findAllByStatusEmail(StatusEmail statusEmail);
}
