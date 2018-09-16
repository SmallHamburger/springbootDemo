package com.jeson.springdemo.service;

import com.jeson.springdemo.domain.Person;
import com.jeson.springdemo.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public List<Person> allPersons(){
        return localRepository.findAll();
    }

    public Person findPersonById(Integer id){
        return localRepository.findById(id).get();
    }

    public Person savePerson(Person person){
        return localRepository.save(person);
    }


}
