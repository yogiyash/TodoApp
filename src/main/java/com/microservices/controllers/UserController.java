package com.microservices.controllers;

import com.microservices.services.UserService;
import com.microservices.utils.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservices.models.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getUsers() {

        return userService.findAll();

    }

    @GetMapping("/{id}")
    public  ResponseEntity<EntityModel<User>> getPerson(@PathVariable Integer id) throws UserNotFoundException {
        User user = userService.findOne(id);
        if (Objects.isNull(user))
            throw new UserNotFoundException("no user found for userid: " + id);
        EntityModel<User> entityModel = EntityModel.of(user);
        //WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn())
        Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel();
        entityModel.add(link);
        return ResponseEntity.ok(entityModel);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deletePerson(@PathVariable Integer id) throws UserNotFoundException{
        if (!userService.delete(id) ) {
            throw new UserNotFoundException("no user found for userid: " + id);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<User> addUsers(@Valid @RequestBody User user) {
        user   = userService.save(user);
        URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
