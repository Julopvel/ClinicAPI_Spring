package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.consult.BookingAConsult;
import com.challenge.clinicAPI.model.consult.dto.DisplayInfoConsultDTO;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consult")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    private BookingAConsult bookingAConsult;

    @Autowired
    public ConsultController(BookingAConsult bookingAConsult) {
        this.bookingAConsult = bookingAConsult;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DisplayInfoConsultDTO> book (@RequestBody @Valid RegisterConsultDTO registerConsultDTO){

        var detailsConsult = bookingAConsult.book(registerConsultDTO);
        return ResponseEntity.ok(detailsConsult);
    }
}
