package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.ValidityException;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidationOutsideConsultationHours implements ConsultValidator{

    public void validate(RegisterConsultDTO registerConsultDTO){
        var dateOfConsult = registerConsultDTO.date();
        var sunday = dateOfConsult.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var scheduleBeforeOpenClinic = dateOfConsult.getHour() < 7;
        var scheduleAfterCloseClinic = dateOfConsult.getHour() > 18;

        if (sunday || scheduleBeforeOpenClinic || scheduleAfterCloseClinic){
            throw new ValidityException("Selected schedule is not available");
        }
    }
}
