package com.challenge.clinicAPI.model.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String number;
    private String city;

    public Address(@NotNull @Valid AddressData address) {
        this.street = address.street();
        this.number = address.number();
        this.city = address.city();
    }

//    public Address updateData(@Valid AddressData address) {
//        this.street = address.street();
//        this.number = address.number();
//        this.city = address.city();
//
//        return this;
//    }
}
