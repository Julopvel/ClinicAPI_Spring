package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import com.challenge.clinicAPI.model.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationActivePatient implements ConsultValidator{

    private PatientRepository patientRepository;

    @Autowired
    public ValidationActivePatient(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void validate(RegisterConsultDTO registerConsultDTO) {

        boolean isPatientActive = patientRepository.findActiveById(registerConsultDTO.idPatient());

        if (!isPatientActive){
            throw new ValidityException("A consult can not be schedule by a non active patient");
        }
    }

}

