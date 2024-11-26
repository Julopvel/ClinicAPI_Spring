package com.challenge.clinicAPI.model.doctor.dto;

import com.challenge.clinicAPI.model.doctor.Doctor;
import com.challenge.clinicAPI.model.doctor.Specialty;

public record ListDoctorDTO(
        Long id,
        String name,
        String email,
        String phone,
        Specialty specialty) {

    public ListDoctorDTO(Doctor doctor){
        this(doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecialty());
    }
}
