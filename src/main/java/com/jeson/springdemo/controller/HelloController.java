package com.jeson.springdemo.controller;

import com.jeson.springdemo.domain.HttpResult;
import com.jeson.springdemo.domain.Person;
import com.jeson.springdemo.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class HelloController {

    private final LocalService localService;

    @Autowired
    public HelloController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/person")
    public HttpResult<List<Person>> allPerson() {
        return new HttpResult<>(localService.allPersons());
    }

    @GetMapping("/person/{id}")
    public HttpResult<Person> findPerson(@PathVariable("id") Integer id) {
        return new HttpResult<>(localService.findPersonById(id));

    }

    @PostMapping("person")
    public HttpResult<Person> addPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return fieldError == null ? new HttpResult<>(HttpResult.Code.FAILED) : new HttpResult<>(HttpResult.Code.FAILED.code, fieldError.getDefaultMessage());
        }
        return new HttpResult<>(localService.savePerson(person));
    }

}
