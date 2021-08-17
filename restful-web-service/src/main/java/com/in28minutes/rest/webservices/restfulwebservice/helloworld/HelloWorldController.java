package com.in28minutes.rest.webservices.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "hello world !!!";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public helloWorldBean helloWorldBean() {
		return new helloWorldBean("Hello world bean !!!");
	}
	
	@GetMapping(path = "/hello-world/{name}")
	public helloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new helloWorldBean("Hello world "+ name);
	}
	
	@GetMapping(path = "/hello-world-Internationlized")
	public String helloWorldInternationlized() {
		return "hello world !!!";
	}
}
