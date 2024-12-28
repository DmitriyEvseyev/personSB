package com.evseyev.personspringboot.controller;

import com.evseyev.personspringboot.model.Person;
import com.evseyev.personspringboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class PersonControllerTest {
    private final PersonService personService;

    @Autowired
    public PersonControllerTest(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/start")
    public String getStart () {
        return "start";
    }

    @GetMapping("/personTest")
    public String getPerson(Model model) {
        List<Person> personList = personService.getPersonsList();
        System.out.println(personList);
        model.addAttribute("personList", personList);
        return "person";
    }

    @GetMapping("/create")
    public String newPerson (Model model) {
        model.addAttribute("person", new Person());
        return "newPerson";
    }

    @PostMapping ("/create")
    public String createPerson (@ModelAttribute ("person") Person person) {
        System.out.println("PERSON  - " + person);
        personService.savePerson(person);
        return "redirect:/personTest";
    }
}

