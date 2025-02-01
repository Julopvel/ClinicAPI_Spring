package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationConsultWithAnticipation implements ConsultValidator{

    public void validate(RegisterConsultDTO registerConsultDTO){
        var dateOfConsult = registerConsultDTO.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateOfConsult).toMinutes();

        if (differenceInMinutes < 30){
            throw new ValidityException("Schedule unavailable");
        }
    }
}
