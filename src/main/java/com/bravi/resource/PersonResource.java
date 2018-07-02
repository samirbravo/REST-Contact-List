package com.bravi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bravi.model.Person;
import com.bravi.repository.PersonRepository;
import com.bravi.resource.event.CreatedResourceEvent;
import com.bravi.resource.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonResource {

	@Autowired 
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Person> findById(@PathVariable Long id){
		return personRepository.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person managedPerson = personService.create(person);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, managedPerson.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(managedPerson);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
		
		personService.update(id, person);
		
		return ResponseEntity.ok(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
}
