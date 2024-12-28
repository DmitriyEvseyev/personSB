package com.evseyev.personspringboot.service;

import com.evseyev.personspringboot.exeptions.NotFoundException;
import com.evseyev.personspringboot.model.Person;
import com.evseyev.personspringboot.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    public void testSavePerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("John Doe");

        personService.savePerson(person);

        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testGetPersonsList() {
        Person person1 = new Person();
        person1.setId(1);
        person1.setName("John Doe");

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Jane Doe");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<Person> persons = personService.getPersonsList();

        assertEquals(2, persons.size());
        assertEquals("John Doe", persons.get(0).getName());
        assertEquals("Jane Doe", persons.get(1).getName());
    }

    @Test
    public void testGetPersonById_Success() {
        Person person = new Person();
        person.setId(1);
        person.setName("John Doe");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Person foundPerson = personService.getPersonById(1);

        assertNotNull(foundPerson);
        assertEquals("John Doe", foundPerson.getName());
    }

    @Test
    public void testGetPersonById_NotFound() {
        when(personRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            personService.getPersonById(1);
        });

        assertEquals("Not found person!!!1", exception.getMessage());
    }

    @Test
    public void testUpdatePerson_Success() {
        Person person = new Person();
        person.setId(1);
        person.setName("John Doe");

        when(personRepository.existsById(1)).thenReturn(true);

        personService.updatePerson(person);

        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testUpdatePerson_NotFound() {
        Person person = new Person();
        person.setId(1);
        person.setName("John Doe");

        when(personRepository.existsById(1)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            personService.updatePerson(person);
        });

        assertEquals("Not found person!!!1", exception.getMessage());
    }

    @Test
    public void testDeletePerson_Success() {
        Integer personId = 1;

        when(personRepository.deletePersonById(personId)).thenReturn(1);

        personService.deletePerson(personId);

        verify(personRepository, times(1)).deletePersonById(personId);
    }

    @Test
    public void testDeletePerson_NotFound() {
        Integer personId = 1;

        when(personRepository.deletePersonById(personId)).thenReturn(0);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            personService.deletePerson(personId);
        });

        assertEquals("Not found person!!!Not found person!!!1", exception.getMessage());
    }
}