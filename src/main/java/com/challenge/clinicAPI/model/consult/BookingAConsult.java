package com.challenge.clinicAPI.model.consult;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import com.challenge.clinicAPI.model.consult.validations.ConsultValidator;
import com.challenge.clinicAPI.model.doctor.Doctor;
import com.challenge.clinicAPI.model.doctor.DoctorRepository;
import com.challenge.clinicAPI.model.patient.Patient;
import com.challenge.clinicAPI.model.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingAConsult {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private List<ConsultValidator> validators;          //This way Spring search all the classes that implement the interface

    @Autowired
    public BookingAConsult(ConsultRepository consultRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, List<ConsultValidator> validators) {
        this.consultRepository = consultRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.validators = validators;
    }

    public void book(RegisterConsultDTO registerConsultDTO){

        if (!patientRepository.existsById(registerConsultDTO.idPatient())){
            throw new ValidityException("The patient with specified ID does not exist!");
        }

        if (registerConsultDTO.idDoctor() != null && !doctorRepository.existsById(registerConsultDTO.idDoctor())){
            throw new ValidityException("The doctor with specified ID does not exist!");
        }

        validators.forEach(v -> v.validate(registerConsultDTO));

        Doctor doctor = chooseDoctor(registerConsultDTO);
        Patient patient = patientRepository.findById(registerConsultDTO.idPatient()).get();

        //id is null because it is automatically incremented within the DB
        Consult consult = new Consult(null, doctor, patient, registerConsultDTO.date());

    }

    /*Method that chooses a random doctor */
    private Doctor chooseDoctor(RegisterConsultDTO registerConsultDTO) {
        if (registerConsultDTO.idDoctor() != null) {
            return doctorRepository.getReferenceById(registerConsultDTO.idDoctor());
        }
        if (registerConsultDTO.specialty() == null){
            throw new ValidityException("It is mandatory to select a specialty when a doctor is not selected");
        }
        return doctorRepository.selectRandomAvailableDoctorInADate(
                registerConsultDTO.specialty(), registerConsultDTO.date());
    }

}
