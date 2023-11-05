package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OrderColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
@Entity
public class Employee {
    
    // @NotNull(message = "must have id (msg from tag)")  // not required because int cannot be null
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int empId;
    
    @Size(min = 2, max = 20, message = "must be legnth 2 to 20 (msg from tag)")
    @NotEmpty(message = "must have name (msg from tag)")
    private String name;
    
    @NotEmpty(message = "must have designation (msg from tag)")
    private String designation;
    
    @Digits(integer = 6, fraction = 0, message = "maximum 6 digit integer (msg from tag)")
    private int salary;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  // no error
    // @DateTimeFormat(pattern="yyyy-mm-dd")  // error
    private LocalDate dob;
    
    // @Enumerated(EnumType.STRING)  // enum better handles when there are thousands of records
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    
    @NotEmpty
    private String citizenship;
    
    @OrderColumn
    private String[] hobbies;
    
    // should have concrete class (such as ArrayList) or hibernate will throw error
    @ElementCollection  // creates table for Employee_spokenLanguages
    @NotNull
    private List<String> spokenLanguages = new ArrayList<>();
    
    @ElementCollection  // creates table for Employee_skills
    @NotNull
    private Set<String> skills = new HashSet<>();
    
    private boolean insured;
    
    private String phoneNumber;
    
    @Email
    @NotEmpty
    private String email;
    
    @Embedded
    @Valid  // should be validated - go check into class
    private Address address;

    public Employee(int empId, String name, String designation, int salary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

}
