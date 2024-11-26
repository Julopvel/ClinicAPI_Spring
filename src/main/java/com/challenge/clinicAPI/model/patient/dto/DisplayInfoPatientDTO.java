package com.challenge.clinicAPI.model.patient.dto;

import com.challenge.clinicAPI.model.address.AddressData;

public record DisplayInfoPatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        AddressData address
) {
}
