package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.ConsultRepository;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDoctorWithOtherConsultInSameSchedule implements ConsultValidator{

    private ConsultRepository consultRepository;

    @Autowired
    public ValidationDoctorWithOtherConsultInSameSchedule(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    public void validate(RegisterConsultDTO registerConsultDTO){

        boolean DoctorGotAnotherConsultInSameSchedule = consultRepository
                .existsByDoctorIdAndDate(registerConsultDTO.idDoctor(), registerConsultDTO.date());

        if (DoctorGotAnotherConsultInSameSchedule){
            throw new ValidityException("The doctor is already occupied at the selected time");
        }

    }
}
