package com.example.project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.project1.model.First;
import com.example.project1.repository.Firstrepository;

public class Firstcontroller {
    @Autowired
    Firstrepository firstrepository;

    @GetMapping("/Show_all")

    public List<First> getAllDetails() {
        return (List<First>) firstrepository.findAll();
    }

    @PutMapping("/insert/{id}")

    public ResponseEntity<First> updateUser(@PathVariable("id") Long id, @RequestBody First first) {

        Optional<First> first2 = firstrepository.findById(id);

        if (first2.isPresent()) {
            First first3 = first2.get();
            first3.setFirstname(first.getFirstname());
            first3.setLastname(first.getLastname());
            first3.setemail(first.getemail());
            return new ResponseEntity<First>(firstrepository.save(first3), HttpStatus.OK);
        }

        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create_user")

    public ResponseEntity<First> createuser(@RequestBody First first) {
        First first2 = firstrepository
                .save(new First(first.getId(), first.getFirstname(), first.getLastname(), first.getemail()));

        return new ResponseEntity<First>(first2, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")

    public ResponseEntity<HttpStatus> deleteAllUser() {
        firstrepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
} 