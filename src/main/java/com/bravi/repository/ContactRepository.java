package com.bravi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bravi.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
