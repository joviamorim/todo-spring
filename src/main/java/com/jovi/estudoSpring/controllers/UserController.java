package com.jovi.estudoSpring.controllers;

import com.jovi.estudoSpring.models.User;
import com.jovi.estudoSpring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable  Long id) {
        User obj = this.userService.findUserById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {
        obj.setId(id);
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
