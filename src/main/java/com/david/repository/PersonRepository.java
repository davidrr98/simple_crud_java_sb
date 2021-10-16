package com.david.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	

}
