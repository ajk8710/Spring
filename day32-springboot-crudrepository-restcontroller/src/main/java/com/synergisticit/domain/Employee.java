package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name="Employee3")
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

}
