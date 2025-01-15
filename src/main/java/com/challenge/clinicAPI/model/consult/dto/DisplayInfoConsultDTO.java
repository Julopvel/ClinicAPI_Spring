package com.challenge.clinicAPI.model.consult.dto;

import java.time.LocalDateTime;

public record DisplayInfoConsultDTO(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date) {
}
