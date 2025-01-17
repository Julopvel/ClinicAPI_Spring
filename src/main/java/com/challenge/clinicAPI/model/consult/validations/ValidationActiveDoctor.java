package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import com.challenge.clinicAPI.model.doctor.DoctorRepository;

public class ValidationActiveDoctor {

    private DoctorRepository doctorRepository;

    public void validate(RegisterConsultDTO registerConsultDTO) {
        //Selection of a doctor is optional.
        if (registerConsultDTO.idDoctor() == null){
            return;
        }

        boolean isDoctorActive = doctorRepository.findActiveById(registerConsultDTO.idDoctor());
        if (!isDoctorActive){
            throw new ValidityException("A consult can not be schedule by a non active patient");
        }
    }
}
