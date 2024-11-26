package com.challenge.clinicAPI.model.patient;

import com.challenge.clinicAPI.model.address.Address;
import com.challenge.clinicAPI.model.patient.dto.RegisterPatientDTO;
import com.challenge.clinicAPI.model.patient.dto.UpdatePatientDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private Boolean active;
    @Embedded
    private Address address;

    public Patient(@Valid RegisterPatientDTO registerPatientDTO) {
        this.name = registerPatientDTO.name();
        this.email = registerPatientDTO.email();
        this.phone = registerPatientDTO.phone();
        this.document = registerPatientDTO.document();
        this.active = true;
        this.address = new Address(registerPatientDTO.address());
    }

    public void updatePatient(@Valid UpdatePatientDTO updatePatientDTO) {
        if (updatePatientDTO.name() != null){
            this.name = updatePatientDTO.name();
        }
        if (updatePatientDTO.email() != null){
            this.email = updatePatientDTO.email();
        }
        if (updatePatientDTO.phone() != null){
            this.phone = updatePatientDTO.phone();
        }
        if (updatePatientDTO.document() != null){
            this.document = updatePatientDTO.document();
        }
//        if (updatePatientDTO.address() != null){
//            this.address = address.updateData(updatePatientDTO.address());
//        }

    }

    public void disablePatient() {
        this.active = false;
    }
}
