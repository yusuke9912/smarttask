package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	boolean existsByEmail(String email);
	Optional<Person> findByEmail(String email);
	Optional<Person> findById(Integer id);
}
