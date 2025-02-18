package com.challenge.clinicAPI.controller;

import com.challenge.clinicAPI.model.consult.BookingAConsult;
import com.challenge.clinicAPI.model.consult.dto.DisplayInfoConsultDTO;
import com.challenge.clinicAPI.model.consult.dto.RegisterConsultDTO;
import com.challenge.clinicAPI.model.doctor.Specialty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest /*This will allow us ti test the Controller*/
@AutoConfigureMockMvc
@WithMockUser   /*With this it simulates that we are already logged into the app*/
@AutoConfigureJsonTesters
class ConsultControllerTest {

    private MockMvc mockMvc;
    private JacksonTester<RegisterConsultDTO> registerConsultDTOJson;
    private JacksonTester<DisplayInfoConsultDTO> displayInfoConsultDTOJson;
    @MockitoBean
    private BookingAConsult bookingAConsult;

    @Autowired
    public ConsultControllerTest(MockMvc mockMvc, JacksonTester<RegisterConsultDTO> registerConsultDTOJson, JacksonTester<DisplayInfoConsultDTO> displayInfoConsultDTOJson) {
        this.mockMvc = mockMvc;
        this.registerConsultDTOJson = registerConsultDTOJson;
        this.displayInfoConsultDTOJson = displayInfoConsultDTOJson;
    }

    @Test
    @DisplayName("Should return http 400 if the request is empty")
    void testBook_returnHttp400() throws Exception {
        var response = mockMvc.perform(post("/consult"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http 200 if the request is valid")
    void testBook_returnHttp200() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var dataDetails = new DisplayInfoConsultDTO(null, 2L, 5L, date);
        /*When any part of the program wants to get into bookingAConsult.book, it should return
        * what is in dataDetails*/
        when(bookingAConsult.book(any())).thenReturn(dataDetails);

        var response = mockMvc.perform(post("/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerConsultDTOJson.write(
                                new RegisterConsultDTO(2L, 5L, date, specialty)
                        ).getJson()))
                .andReturn().getResponse();

        var expectedJson = displayInfoConsultDTOJson.write(
                dataDetails
        ).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}