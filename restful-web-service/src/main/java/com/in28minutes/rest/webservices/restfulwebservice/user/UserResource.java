package com.in28minutes.rest.webservices.restfulwebservice.user;

import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@Autowired
	private UserDAO userService;
	
	//retrieve users
	@GetMapping(value = "/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	//retrieve user
	@GetMapping(value = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable long id){
		User user = userService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		
		model.add(linkTo.withRel("All-Users"));
		return model;
	}
	
	@PostMapping(value = "/users")
	public void createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public void deleteUser(@PathVariable long id){
		User user = userService.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);
	
	}
}
