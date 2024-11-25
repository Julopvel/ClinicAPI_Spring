package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.doctor.Doctor;
import com.challenge.clinicAPI.model.doctor.DoctorRepository;
import com.challenge.clinicAPI.model.doctor.dto.RegisterDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.UpdateDoctorDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void registerDoctor(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO){
        doctorRepository.save(new Doctor(registerDoctorDTO));
    }

    @GetMapping
    public List<Doctor> listDoctors(){
        return doctorRepository.findAll();
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO){
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorDTO.id());
        doctor.updateData(updateDoctorDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void disableDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();
    }

}
