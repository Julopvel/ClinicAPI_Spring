package com.challenge.clinicAPI.model.patient;

import com.challenge.clinicAPI.model.patient.dto.ListPatientDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<ListPatientDTO> findByActiveTrue();

    @Query("""
            select p.active from Patient p where p.id = :idPatient
            """)
    boolean findActiveById(Long idPatient);
}
