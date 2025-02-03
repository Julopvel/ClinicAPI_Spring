package com.challenge.clinicAPI.model.doctor;

import com.challenge.clinicAPI.model.doctor.dto.DisplayInfoDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.ListDoctorDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<ListDoctorDTO> findByActiveTrue();

    @Query("""
            select d from Doctor d
            where
            d.active = TRUE
            and
            d.specialty = :specialty
            and d.id not in(
                select c.doctor.id from Consult c
                where
                c.date = :date
            )
            order by function('RAND')
            limit 1
            
            """)
    Doctor selectRandomAvailableDoctorInADate(Specialty specialty, @NotNull @Future LocalDateTime date);

    @Query("""
            select d.active
            from Doctor d
            where
            d.id = :idDoctor
            """)
    boolean findActiveById(Long idDoctor);
}
