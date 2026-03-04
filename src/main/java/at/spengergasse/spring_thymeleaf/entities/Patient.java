package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "p_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_birthday")
    private LocalDate birthday;

    public Patient() {}

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
