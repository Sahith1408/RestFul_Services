package com.in28minutes.rest.webservices.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filters")
	public MappingJacksonValue getBean() {
		Bean bean = new Bean("value1", "value2", "value3");
		//field1, field2
		 SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				 .filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("beanFilter", filter);
		
		MappingJacksonValue map = new MappingJacksonValue(bean); 
		map.setFilters(filters);
		return map;
	}
	
	@GetMapping("/filters-list")
	public MappingJacksonValue getListBean() {
		 List<Bean> list = Arrays.asList(new Bean("value11", "value21", "value31"),
		 new Bean("value1", "value2", "value3"));
		 SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				 .filterOutAllExcept("field3","field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("beanFilter", filter);
		
		MappingJacksonValue map = new MappingJacksonValue(list); 
		map.setFilters(filters);
		return map;
	}
}
