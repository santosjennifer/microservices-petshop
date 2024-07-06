package com.petshop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.petshop.dto.PersonDto;

@FeignClient(name = "ms-person")
public interface PersonClient {

	@GetMapping("api/person/{id}")
	PersonDto findPersonById(@PathVariable String id);
	
}
