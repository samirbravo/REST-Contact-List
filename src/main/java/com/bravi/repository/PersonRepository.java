package com.bravi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bravi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
