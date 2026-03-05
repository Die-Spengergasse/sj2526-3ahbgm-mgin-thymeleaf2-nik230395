package at.spengergasse.spring_thymeleaf.services;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Page<Patient> getPatientsPaged(int page, int size, String sort) {
        Sort sortOrder = sort.equals("desc")
                ? Sort.by("birthday").descending()
                : Sort.by("birthday").ascending();

        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return patientRepository.findAll(pageable);
    }
}
