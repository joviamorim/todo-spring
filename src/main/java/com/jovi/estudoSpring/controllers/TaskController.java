package com.jovi.estudoSpring.controllers;

import com.jovi.estudoSpring.models.Task;
import com.jovi.estudoSpring.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
@Validated
public class TaskController {

    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
        Task obj = this.taskService.findTaskById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllTasksById(@PathVariable Long userId) {
        List<Task> objs = this.taskService.findAllTasksById(userId);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj) {
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task obj, @PathVariable Long id) {
        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }



}
