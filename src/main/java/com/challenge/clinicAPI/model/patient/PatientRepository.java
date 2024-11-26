package com.challenge.clinicAPI.model.patient;

import com.challenge.clinicAPI.model.patient.dto.ListPatientDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<ListPatientDTO> findByActiveTrue();
}
