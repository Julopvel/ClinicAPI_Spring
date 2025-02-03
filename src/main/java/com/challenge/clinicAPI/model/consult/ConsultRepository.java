package com.challenge.clinicAPI.model.consult;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    boolean existsByPatientIdAndDateBetween(@NotNull Long idPatient, LocalDateTime initialSchedule, LocalDateTime lastSchedule);

    boolean existsByDoctorIdAndDate(Long idDoctor, @NotNull @Future LocalDateTime date);
}
