package com.evseyev.personspringboot.service;

import com.evseyev.personspringboot.exeptions.NotFoundException;
import com.evseyev.personspringboot.model.Person;
import com.evseyev.personspringboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getPersonsList() {
        return personRepository.findAll();
    }

    public Person getPersonById(Integer personId) {
        Optional<Person> person = personRepository.findById(personId);
        return person.orElseThrow(() -> new NotFoundException("Not found person!!!" + personId));
    }

    @Transactional
    public void updatePerson(Person person) {
        if (personRepository.existsById(person.getId())) {
            personRepository.save(person);
        } else throw new NotFoundException("Not found person!!!" + person.getId());
    }

    @Transactional
    public void deletePerson(Integer personId) {
        int deleteStatus = personRepository.deletePersonById(personId);
        if (deleteStatus == 0)
            throw new NotFoundException("Not found person!!!" + "Not found person!!!" + personId);
    }
}


