package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.ConsultRepository;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPatientsWithoutConsultTheSameDay implements ConsultValidator{

    private ConsultRepository consultRepository;

    @Autowired
    public ValidationPatientsWithoutConsultTheSameDay(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    public void validate(RegisterConsultDTO registerConsultDTO){
        var initialSchedule = registerConsultDTO.date().withHour(7);
        var lastSchedule = registerConsultDTO.date().withHour(18);
        boolean patientGotAnotherConsultInTheDay = consultRepository
                .existsByPatientIdAndDateBetween(registerConsultDTO.idPatient(), initialSchedule, lastSchedule);

        if (patientGotAnotherConsultInTheDay){
            throw new ValidityException("The patient already got a consult");
        }
    }
}
