package com.galvanize.controllers;

import com.galvanize.entities.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @GetMapping()
    public Person sayHello(@RequestParam String name, @RequestParam Date birthDate, @RequestParam String email, @RequestParam String address){

        Person person = new Person(name, birthDate, email, address);
        return person;
    }

  @PostMapping()
  public Person helloPerson(@RequestBody Person person){
        return person;
  }

}
