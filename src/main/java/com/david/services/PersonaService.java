package com.david.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.repository.PersonRepository;

import com.david.models.Person;

@Service
public class PersonaService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person create (Person person) {
		return personRepository.save(person);
	}
	
	public List<Person> getAll () {
		return personRepository.findAll();
	}
	
	public void delete (Person person) {
		personRepository.delete(person);
	}
	
	public boolean deleteById (long id) {
		Optional<Person> person = this.getPersonById(id);
		if(person.isPresent()) {
			personRepository.delete(person.get());
			return true;
		}
		return false;	
	}
		
	public Optional<Person> getPersonById(long id) {
		return personRepository.findById(id);
	}

	public Optional<Person> editPersonById(long id, Person person_edit) {
		Optional<Person> optinal_person = personRepository.findById(id);
		if (optinal_person .isPresent()){
			Person person =optinal_person.get();
			String firstname_edit=person_edit.getFirstname();
			if (firstname_edit!=null)
				person.setFirstname(firstname_edit);
			
			String lastName_edit=person_edit.getFirstname();
			if (firstname_edit!=null)
				person.setLastname(lastName_edit);
			
			String email_edit=person_edit.getFirstname();
			if (firstname_edit!=null)
				person.setEmail(email_edit);
			
			personRepository.save(person);
			return optinal_person;
		}	
		return null;
	}

}
