package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.repositories.PatientRepository;
import at.spengergasse.spring_thymeleaf.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    @GetMapping
    public String index(Model model) { // landet auf /patients
        model.addAttribute("patients", patientService.getSortedPatients()); // key patients, value liste
        model.addAttribute("patient", new Patient()); // key patient, value neues Patient Objekt
        return "index"; // index.html gerendert
    }

    // Erstmal für neuen Patienten anlegen
    @GetMapping("/new")
    public String newForm(Model model) { // Patient klickt in idnex auf new dann landet er hier
        model.addAttribute("patient", new Patient()); // key patient
        return "new"; // return mal website
    }

    // dann zum commiten
    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) { // nach button click in new.html
        patientRepository.save(patient); // speichert
        return "redirect:/patients"; // zurück auf home
    }

    // editieren Formular
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) { // edit seite / id aus url
        model.addAttribute("patient", patientRepository.findById(id).orElseThrow()); // zeig patient id key
        return "edit"; // gib id
    }

    // editieren
    @PostMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id ,@ModelAttribute Patient updatedPatient) {
        Patient existing = patientRepository.findById(id).orElseThrow();
        existing.setName(updatedPatient.getName());
        existing.setBirthday(updatedPatient.getBirthday());
        patientRepository.save(existing);
        return "redirect:/patients";
    }


    //löschen halt
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
