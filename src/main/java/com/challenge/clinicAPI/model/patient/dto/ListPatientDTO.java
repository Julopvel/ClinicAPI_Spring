package com.challenge.clinicAPI.model.patient.dto;

import com.challenge.clinicAPI.model.patient.Patient;

public record ListPatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document) {

    public ListPatientDTO(Patient patient){
        this(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument()
                );
    }
}
