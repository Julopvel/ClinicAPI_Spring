package com.challenge.clinicAPI.model.doctor;

import com.challenge.clinicAPI.model.address.Address;
import com.challenge.clinicAPI.model.doctor.dto.RegisterDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.UpdateDoctorDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(@Valid RegisterDoctorDTO registerDoctorDTO) {
        this.name = registerDoctorDTO.name();
        this.email = registerDoctorDTO.email();
        this.phone = registerDoctorDTO.phone();
        this.document = registerDoctorDTO.document();
        this.active = true;
        this.specialty = registerDoctorDTO.specialty();
        this.address = new Address(registerDoctorDTO.address());
    }

    public void updateData(@Valid UpdateDoctorDTO updateDoctorDTO) {
        if (updateDoctorDTO.name() != null){
            this.name = updateDoctorDTO.name();
        }
        if (updateDoctorDTO.email() != null){
            this.email = updateDoctorDTO.email();
        }
        if (updateDoctorDTO.phone() != null){
            this.phone = updateDoctorDTO.phone();
        }
        if (updateDoctorDTO.document() != null){
            this.document = updateDoctorDTO.document();
        }
//        if (updateDoctorDTO.address() != null){
//            this.address = new Address(updateDoctorDTO.address());
//        }
    }

    public void disableDoctor() {
        this.active = false;
    }
}
