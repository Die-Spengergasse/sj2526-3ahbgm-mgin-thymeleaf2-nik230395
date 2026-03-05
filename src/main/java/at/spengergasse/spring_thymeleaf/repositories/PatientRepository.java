package at.spengergasse.spring_thymeleaf.repositories;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("SELECT p FROM Patient p ORDER BY p.birthday asc")
    List<Patient> findAllSortedByBirthdayAsc();

    @Query("SELECT p FROM Patient p ORDER BY p.birthday desc")
    List<Patient> findAllSortedByBirthdayDesc();
}
