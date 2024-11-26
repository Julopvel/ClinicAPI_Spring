package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.address.AddressData;
import com.challenge.clinicAPI.model.doctor.dto.DisplayInfoDoctorDTO;
import com.challenge.clinicAPI.model.patient.Patient;
import com.challenge.clinicAPI.model.patient.PatientRepository;
import com.challenge.clinicAPI.model.patient.dto.DisplayInfoPatientDTO;
import com.challenge.clinicAPI.model.patient.dto.ListPatientDTO;
import com.challenge.clinicAPI.model.patient.dto.RegisterPatientDTO;
import com.challenge.clinicAPI.model.patient.dto.UpdatePatientDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping
    public ResponseEntity<DisplayInfoPatientDTO> registerPatient(@RequestBody @Valid RegisterPatientDTO registerPatientDTO,
                                                                 UriComponentsBuilder uriComponentsBuilder){

        Patient patient = patientRepository.save(new Patient(registerPatientDTO));

        DisplayInfoPatientDTO displayInfoPatientDTO = new DisplayInfoPatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument(),
                new AddressData(
                        patient.getAddress().getStreet(),
                        patient.getAddress().getNumber(),
                        patient.getAddress().getCity())
        );

        URI uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId())
                .toUri();
        return ResponseEntity.created(uri).body(displayInfoPatientDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListPatientDTO>> listPatient(){
        return ResponseEntity.ok(patientRepository.findAll().stream()
                .map(ListPatientDTO::new)
                .toList());
    }

    @GetMapping
    public ResponseEntity<List<ListPatientDTO>> listActivePatients(){
        return ResponseEntity.ok(patientRepository.findByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListPatientDTO> listPatientById(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new ListPatientDTO(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DisplayInfoPatientDTO> updatePatient(@RequestBody @Valid UpdatePatientDTO updatePatientDTO){
        Patient patient = patientRepository.getReferenceById(updatePatientDTO.id());
        patient.updatePatient(updatePatientDTO);

        return ResponseEntity.ok(
                new DisplayInfoPatientDTO(
                        patient.getId(),
                        patient.getName(),
                        patient.getEmail(),
                        patient.getPhone(),
                        patient.getDocument(),
                        new AddressData(
                                patient.getAddress().getStreet(),
                                patient.getAddress().getNumber(),
                                patient.getAddress().getCity()
                                )
                )
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> disablePatient(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.disablePatient();

        return ResponseEntity.noContent().build();

    }
}
