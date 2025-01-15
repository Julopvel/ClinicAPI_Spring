package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.consult.dto.DisplayInfoConsultDTO;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consult")
public class ConsultController {

    @PostMapping
    public ResponseEntity<DisplayInfoConsultDTO> book (@RequestBody @Valid RegisterConsultDTO data){
        System.out.println(data);
        return ResponseEntity.ok(new DisplayInfoConsultDTO(null, null, null, null));
    }
}
