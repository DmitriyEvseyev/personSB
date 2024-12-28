package com.evseyev.personspringboot.repository;

import com.evseyev.personspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    int deletePersonById (Integer personId);
}
