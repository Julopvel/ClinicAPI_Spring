package com.challenge.clinicAPI.model.consult.dto;

import com.challenge.clinicAPI.model.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisterConsultDTO(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty
        /*Specialty added to complement the business rule of selecting a random doctor */
) {
}
