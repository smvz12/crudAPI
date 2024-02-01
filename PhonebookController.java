package com.java.Phonebook.controller;

import com.java.Phonebook.model.Phonebook;
import com.java.Phonebook.repo.BookRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class PhonebookController {

    private final BookRepo bookRepo;

    public PhonebookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/getAllPeople")
    public ResponseEntity<List<Phonebook>> getAllPeople() {
    try {
        List<Phonebook> phonebookList = new ArrayList<>(bookRepo.findAll());

if (phonebookList.isEmpty()){
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

    return new ResponseEntity<>(phonebookList, HttpStatus.OK);
    } catch (Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
@GetMapping("/getPersonById/{id}")
    public ResponseEntity<Phonebook> getPersonById(@PathVariable Long id) {
    Optional<Phonebook> bookData = bookRepo.findById(id);

    return bookData.map(phonebook -> new ResponseEntity<>(phonebook, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));


}

    @PostMapping("/addPerson")
    public ResponseEntity<Phonebook> addPerson(@RequestBody Phonebook person){
    Phonebook personObj = bookRepo.save(person);

    return new ResponseEntity<>(personObj, HttpStatus.OK);
    }
    @PostMapping("/updatePersonById/{id}")
    public ResponseEntity<Phonebook> updatePersonById(@PathVariable Long id, @RequestBody Phonebook newBookData){
    Optional<Phonebook> outdated = bookRepo.findById(id);

    if (outdated.isPresent()){
        Phonebook updateBookData = outdated.get();
        updateBookData.setName(newBookData.getName());
        updateBookData.setPhoneNumber(newBookData.getPhoneNumber());

        Phonebook personObj = bookRepo.save(updateBookData);
        return new ResponseEntity<>(personObj, HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deletePersonId/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable Long id){
    bookRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);

    }
}
