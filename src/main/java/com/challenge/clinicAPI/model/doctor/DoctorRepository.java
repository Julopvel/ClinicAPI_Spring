package com.challenge.clinicAPI.model.doctor;

import com.challenge.clinicAPI.model.doctor.dto.DisplayInfoDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.ListDoctorDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<ListDoctorDTO> findByActiveTrue();
}
