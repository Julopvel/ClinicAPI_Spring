package com.challenge.clinicAPI.model.consult.dto;

import com.challenge.clinicAPI.model.consult.Consult;

import java.time.LocalDateTime;

public record DisplayInfoConsultDTO(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date) {
    public DisplayInfoConsultDTO(Consult consult) {
        this(consult.getId(),
             consult.getDoctor().getId(),
             consult.getPatient().getId(),
             consult.getDate());
    }
}
