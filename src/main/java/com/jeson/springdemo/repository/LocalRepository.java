package com.jeson.springdemo.repository;

import com.jeson.springdemo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Person, Integer> {

}
