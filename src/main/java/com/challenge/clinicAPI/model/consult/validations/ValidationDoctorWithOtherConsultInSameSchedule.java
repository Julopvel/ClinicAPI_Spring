package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.ConsultRepository;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;

public class ValidationDoctorWithOtherConsultInSameSchedule {

    private ConsultRepository consultRepository;

    public void validate(RegisterConsultDTO registerConsultDTO){

        boolean DoctorGotAnotherConsultInSameSchedule = consultRepository
                .existsByDoctorIdAndDate(registerConsultDTO.idDoctor(), registerConsultDTO.date());

        if (DoctorGotAnotherConsultInSameSchedule){
            throw new ValidityException("The doctor is already occupied at the selected time");
        }

    }
}
