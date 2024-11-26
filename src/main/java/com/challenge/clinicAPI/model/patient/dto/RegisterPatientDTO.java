package com.challenge.clinicAPI.model.patient.dto;

import com.challenge.clinicAPI.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterPatientDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{5,7}")
        String document,
        @NotNull
        @Valid
        AddressData address
) {
}
