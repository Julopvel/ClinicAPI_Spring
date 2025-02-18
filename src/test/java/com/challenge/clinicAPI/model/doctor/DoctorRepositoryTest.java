package com.challenge.clinicAPI.model.doctor;

import com.challenge.clinicAPI.model.address.AddressData;
import com.challenge.clinicAPI.model.consult.Consult;
import com.challenge.clinicAPI.model.doctor.dto.RegisterDoctorDTO;
import com.challenge.clinicAPI.model.patient.Patient;
import com.challenge.clinicAPI.model.patient.dto.RegisterPatientDTO;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  /*When testing a repository and/or When a test needs the persistence layer, this annotation will do the trick*/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) /*To use an embedded DB or the same as development*/
@ActiveProfiles("test") /*This way Spring will identify this new profile, and will take the information from application-test.properties file*/
class DoctorRepositoryTest {

    private DoctorRepository doctorRepository;
    private EntityManager em;

    @Autowired
    public DoctorRepositoryTest(DoctorRepository doctorRepository, EntityManager em) {
        this.doctorRepository = doctorRepository;
        this.em = em;
    }
    /*The execution of tests, should follow this principle:
    * Given a certain context,
    * when executed the required method,
    * then it should assert a result*/
    @Test
    @DisplayName("Should return null if input doctor exists and is not available in that date")
    void testSelectRandomAvailableDoctorInADate_doctorExistsAndNotAvailable() {
        //Given
        var nextMonday10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var doctor = registerDoctor("doctor1", "doctor1@gmail.com", "12345", Specialty.CARDIOLOGY);
        var patient = registerPatient("patient1", "patient1@gmail.com", "7890");
        registerConsult(doctor, patient, nextMonday10Am);
        //when or act
        var availableDoctor = doctorRepository.selectRandomAvailableDoctorInADate(Specialty.CARDIOLOGY, nextMonday10Am);
        //then or assert
        assertNull(availableDoctor);
    }

    @Test
    @DisplayName("Should return a doctor if input doctor is available in that date")
    void testSelectRandomAvailableDoctorInADate_doctorAvailable() {
        var nextMonday10Am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var doctor = registerDoctor("doctor1", "doctor1@gmail.com", "12345", Specialty.CARDIOLOGY);

        var availableDoctor = doctorRepository.selectRandomAvailableDoctorInADate(Specialty.CARDIOLOGY, nextMonday10Am);
        assertEquals(doctor, availableDoctor);
        Assertions.assertThat(availableDoctor).isEqualTo(doctor);
    }

    @Test
    void findActiveById() {
    }

    /*private methods that will help insert data into the test DataBase
    * through persist method of EntityManager*/

    private void registerConsult(Doctor doctor, Patient patient, LocalDateTime date){
        em.persist(new Consult(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String name, String email, String document, Specialty specialty){
        var doctor = new Doctor(doctorData(name, email, document, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String document){
        var patient = new Patient(patientData(name, email, document));
        em.persist(patient);
        return patient;
    }

    private RegisterDoctorDTO doctorData(String name, String email, String document, Specialty specialty){
        return new RegisterDoctorDTO(
                name,
                email,
                "987654",
                document,
                specialty,
                addressData()
        );
    }

    private RegisterPatientDTO patientData(String name, String email, String document){
        return new RegisterPatientDTO(
                name,
                email,
                "123456",
                document,
                addressData()
        );
    }

    private AddressData addressData(){
        return new AddressData(
                "street x",
                "123",
                "citi z"
        );
    }

}