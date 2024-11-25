package com.challenge.clinicAPI.model.doctor.dto;

import com.challenge.clinicAPI.model.address.AddressData;
import com.challenge.clinicAPI.model.doctor.Specialty;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorDTO(
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
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address) {
}
