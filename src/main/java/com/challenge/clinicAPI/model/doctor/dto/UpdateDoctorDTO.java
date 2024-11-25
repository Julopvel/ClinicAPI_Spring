package com.challenge.clinicAPI.model.doctor.dto;

import com.challenge.clinicAPI.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String phone,
        String document,
        @Valid
        AddressData address
) {
}
