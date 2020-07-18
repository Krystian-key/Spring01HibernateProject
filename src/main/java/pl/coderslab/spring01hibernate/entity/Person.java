package pl.coderslab.spring01hibernate.entity;

import pl.coderslab.spring01hibernate.validator.IsOver18YO;
import pl.coderslab.spring01hibernate.validator.IsOverXYO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
/*    @IsOver18YO*/
    @IsOverXYO(8)
    private int yearOfBirth;


    @OneToOne
    @JoinColumn(name = "details_id",
            unique=true)
    private PersonDetails details;

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    public Person() {
    }


    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Person setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public Person setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", details=" + details +
                '}';
    }
}