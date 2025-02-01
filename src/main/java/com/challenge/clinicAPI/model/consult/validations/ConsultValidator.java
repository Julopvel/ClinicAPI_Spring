package com.challenge.clinicAPI.model.consult.validations;

import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;


public interface ConsultValidator {

    void validate(RegisterConsultDTO registerConsultDTO);
}
