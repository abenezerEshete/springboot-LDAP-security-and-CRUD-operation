package com.memorynotfound.ldap.controller;


import com.memorynotfound.ldap.model.Person;
import com.memorynotfound.ldap.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    PersonRepository personRepository;

    @GetMapping("/list")
    public List<Person> personList() {


        List<Person> personList = personRepository.findAll();

        FileWriter fileWriter = null;
        try {

            FileWriter finalFileWriter = new FileWriter(new File("sample.txt"));;
            personList.stream().peek(person -> {
                try {
                    finalFileWriter.append(person.toString()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            finalFileWriter.write(personList.toString());
            finalFileWriter.flush();
            finalFileWriter.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }

    @GetMapping("/get/{uid}")
    public Person getPersonByuid(@PathVariable String uid) {

        Person person = personRepository.findOne(uid);


        return person;
    }

    @PutMapping("/update")
    public String updatePerson(@RequestBody Person newPerson) {


        personRepository.update(newPerson);
        return "successfully Updated";
    }

    @PostMapping("/save")
    public String savePerson(@RequestBody Person person) {

        personRepository.create(person);
        return "successfully Saved";
    }

    @DeleteMapping("delete/{uid}")
    public String deletePerson(@PathVariable String uid) {

        Person person = personRepository.findOne(uid);
        if (person != null) {
            personRepository.delete(person);
            return "Successfully deleted";
        }
        return "couldn't find person";


    }

}
