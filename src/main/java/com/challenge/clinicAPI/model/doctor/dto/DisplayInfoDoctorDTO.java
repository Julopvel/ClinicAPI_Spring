package com.challenge.clinicAPI.model.doctor.dto;

import com.challenge.clinicAPI.model.address.AddressData;
import com.challenge.clinicAPI.model.doctor.Specialty;

public record DisplayInfoDoctorDTO(
        Long id,
        String name,
        String email,
        String phone,
        Specialty specialty,
        AddressData address
) {
}
