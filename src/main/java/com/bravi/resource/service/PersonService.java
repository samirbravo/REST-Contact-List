package com.bravi.resource.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.bravi.model.Contact;
import com.bravi.model.Person;
import com.bravi.repository.ContactRepository;
import com.bravi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	public Person update(Long id, Person person) {
		
		Optional<Person> managedPerson = personRepository.findById(id);
		
		if(!managedPerson.isPresent())
			throw new EmptyResultDataAccessException(1);
		
		person.setId(id);
		
		for (Contact contact : person.getContacts()) {
			contactRepository.save(contact);
		}
		return personRepository.save(person);
	}

	public Person create(Person person) {
		
		Person managedPerson = personRepository.save(person); 
		
		for (Contact contact : person.getContacts()) {
			if(contact.getId() == null) {
				contactRepository.save(contact);
			}
		}
		return managedPerson;		
	}
	
}
