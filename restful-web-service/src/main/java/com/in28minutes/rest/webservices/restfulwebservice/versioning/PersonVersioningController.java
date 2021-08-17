package com.in28minutes.rest.webservices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public Personv1 getPersonv1() {
		return new Personv1("Sahith");
	}
	
	@GetMapping("v2/person")
	public Personv2 getPersonv2() {
		return new Personv2(new Name("sahith", "manchi"));
	}
	
	@GetMapping(value = "/person/param", params = "version=1")
	public Personv1 getPersonv1param() {
		return new Personv1("Sahith");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public Personv2 getPersonv2param() {
		return new Personv2(new Name("sahith", "manchi"));
	}
	
	@GetMapping(value = "/person/header", headers  = "X-API-VERSION=1")
	public Personv1 headerv1() {
		return new Personv1("Sahith");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public Personv2 headerv2() {
		return new Personv2(new Name("sahith", "manchi"));
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public Personv1 producesv1() {
		return new Personv1("Sahith");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public Personv2 producesv2() {
		return new Personv2(new Name("sahith", "manchi"));
	}
}
