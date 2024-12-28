package com.evseyev.personspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Person implements Serializable {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Id should be greater than 0.")
    private Integer id;

    @Column(name = "person_name")
    @NotEmpty(message = "Name should be empty.")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters.")
    private String name;

    @Column(name = "person_age")
    @Min(value = 10, message = "Id should be greater than 10.")
    private Integer age;
}
