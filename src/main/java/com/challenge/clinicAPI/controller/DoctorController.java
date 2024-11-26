package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.address.AddressData;
import com.challenge.clinicAPI.model.doctor.Doctor;
import com.challenge.clinicAPI.model.doctor.DoctorRepository;
import com.challenge.clinicAPI.model.doctor.dto.DisplayInfoDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.ListDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.RegisterDoctorDTO;
import com.challenge.clinicAPI.model.doctor.dto.UpdateDoctorDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    public ResponseEntity<DisplayInfoDoctorDTO> registerDoctor(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor = doctorRepository.save(new Doctor(registerDoctorDTO));

        DisplayInfoDoctorDTO displayInfoDoctorDTO = new DisplayInfoDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                new AddressData(
                        doctor.getAddress().getStreet(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getCity())
        );
        URI uri = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(displayInfoDoctorDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListDoctorDTO>> listDoctors(){
        return ResponseEntity.ok(doctorRepository.findAll().stream()
                .map(ListDoctorDTO::new)
                .toList());
    }

    @GetMapping
    public ResponseEntity<List<ListDoctorDTO>> listActiveDoctors(){
        return ResponseEntity.ok(doctorRepository.findByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListDoctorDTO> listDoctorById(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new ListDoctorDTO(doctor));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DisplayInfoDoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO){
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorDTO.id());
        doctor.updateData(updateDoctorDTO);

        return ResponseEntity.ok(new DisplayInfoDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                new AddressData(doctor.getAddress().getStreet(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getCity()))
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> disableDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();

        return ResponseEntity.noContent().build();
    }

}
