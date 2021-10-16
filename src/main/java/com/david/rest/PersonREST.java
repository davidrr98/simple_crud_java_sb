package com.david.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.models.Person;
import com.david.services.PersonaService;

@RestController
@RequestMapping("/person")
public class PersonREST {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping
	private ResponseEntity<List<Person>> listPersons (){
		return ResponseEntity.ok(personaService.getAll());
	}
		
	@GetMapping (value = "{id}")
	private ResponseEntity<Optional<Person>> getPersonById(@PathVariable ("id") Long id){
		return ResponseEntity.ok(personaService.getPersonById(id));
		
	}
	
	@PostMapping
	private ResponseEntity<Person> guardar (@Valid @RequestBody Person person, BindingResult result){
		Person person_create = personaService.create(person);
		if( result.hasErrors()) {
			System.out.println("ERROR");
		}
		try {
			return ResponseEntity.created(new URI("/api/persona"+person_create.getId())).body(person_create);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping (value = "{id}")
	private ResponseEntity<Optional<Person>> editPersonById(@PathVariable ("id") Long id, @RequestBody @Valid Person person){
		return ResponseEntity.ok(personaService.editPersonById(id, person));
	}
			
	
	@DeleteMapping (value = "{id}")
	private ResponseEntity<Void> deletePersonById(@PathVariable ("id") Long id){
		boolean status = personaService.deleteById(id);
		if (status)
			return ResponseEntity.ok().build();	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}

