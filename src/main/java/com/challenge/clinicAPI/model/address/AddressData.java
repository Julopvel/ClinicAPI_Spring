package com.challenge.clinicAPI.model.address;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String number,
        @NotBlank
        String city) {
}
