package com.evseyev.personspringboot.controller;

import com.evseyev.personspringboot.exeptions.CreatedExeption;
import com.evseyev.personspringboot.model.Person;
import com.evseyev.personspringboot.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addPerson(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMes = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMes.append("-").append(error.getDefaultMessage()).append("; ");
            }
            throw new CreatedExeption(errorMes.toString());
        }
        log.info("NEW,  person -  {}", person);
        personService.savePerson(person);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Person> getDealersList() {
        List<Person> personList = personService.getPersonsList();
        log.info("personList - {}", personList);
        return personList;
    }

    @GetMapping("/{id}")
    public Person getPerson (@PathVariable("id") Integer personId) {
        Person person = personService.getPersonById(personId);
        log.info("person - {}", person);
        return person;
    }


    @PutMapping()
    public ResponseEntity<HttpStatus> updatePerson(@RequestBody Person person) {

        log.info("UPDATE, person - {}", person);

        personService.updatePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delPerson(@PathVariable("id") Integer personId) {

        log.info("DELETE, personId - {}", personId);

        personService.deletePerson(personId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
