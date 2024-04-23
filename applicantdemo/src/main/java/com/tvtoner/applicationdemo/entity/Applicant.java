package com.tvtoner.applicationdemo.entity;

import com.tvtoner.applicationdemo.validation.DepartmentKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="applicants")
public class Applicant {

    //Define the fields
    /**
     * Defines the database correlation with the parameter here and the one in the database, also sets generation strategy of the
     * primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Defines what makes the first name valid in the html form, if not an error message is displayed
     */
    @NotNull(message = "First Name Required")
    @Size(min=1, message = "First Name Required")
    @Column(name="first_name")
    private String firstName;

    /**
     * Defines what makes the last name valid in the html form, if not an error message is displayed
     */
    @NotNull(message = "Last Name Required")
    @Size(min=1, message = "Last Name Required")
    @Column(name="last_name")
    private String lastName;

    /**
     * Uses regX definition of an email, to confirm that the data given is a email
     */
    @NotNull(message = "Email Required")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Enter a real email")
    @Column(name="email")
    private String email;

    /**
     * Defined as an Integer, so we can handle null expressions, also in the messages.proeprties is a custom message, if
     * a different type of value is given instead of an integer
     */
    @NotNull(message = "Experience Required")
    @Column(name="hours_of_experience")
    private Integer hoursOfExperience;

    /**
     * Uses regX to define what the postal code should look like
     */
    @NotNull(message = "Postal Code Required")
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Enter a Valid Postal Address (5 chars/digits)")
    @Column(name="postal_code")
    private String postalCode;

    /**
     * Uses a custom annotation to define the department key's that are valid
     */
    @DepartmentKey(value = {"DEV", "ADMIN", "CEO"})
    @NotNull(message = "Department Key Required")
    @Column(name="department_key")
    private String departmentKey;

    //Constructors
    public Applicant(){

    }

    public Applicant(int id, String firstName, String lastName,
                     String email, Integer hoursOfExperience,
                     String postalCode, String departmentKey) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hoursOfExperience = hoursOfExperience;
        this.postalCode = postalCode;
        this.departmentKey = departmentKey;
    }

    // Setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHoursOfExperience() {
        return hoursOfExperience;
    }

    public void setHoursOfExperience(Integer hoursOfExperience) {
        this.hoursOfExperience = hoursOfExperience;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDepartmentKey() {
        return departmentKey;
    }

    public void setDepartmentKey(String departmentKey) {
        this.departmentKey = departmentKey;
    }

    //To String Method

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hoursOfExperience='" + hoursOfExperience + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", departmentKey='" + departmentKey + '\'' +
                '}';
    }
}
