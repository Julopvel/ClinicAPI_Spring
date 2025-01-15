package com.challenge.clinicAPI.model.consult.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisterConsultDTO(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date
) {
}
