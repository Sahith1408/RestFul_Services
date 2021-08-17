package com.in28minutes.rest.webservices.restfulwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserJpaResource {

	@Autowired
	private UserDAO userService;
	
	@Autowired
	private UserRepository userRepository;
	
	//retrieve users
	@GetMapping(value = "/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//retrieve user
	@GetMapping(value = "/jpa/users/{id}")
	public EntityModel<Optional<User>> getUser(@PathVariable long id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id : "+id);
		EntityModel<Optional<User>> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		
		model.add(linkTo.withRel("All-Users"));
		return model;
	}
	
	@PostMapping(value = "/jpa/users")
	public void createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
	}
	
	@DeleteMapping(value = "/jpa/users/{id}")
	public void deleteUser(@PathVariable long id){
		User user = userService.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id : "+id);
	
	}
}
