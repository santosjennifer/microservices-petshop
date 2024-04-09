package com.petshop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.petshop.dto.AnimalDto;

@FeignClient(name = "ms-pet")
public interface AnimalClient {
	
	@GetMapping("/api/animal/{guardianId}/list")
	List<AnimalDto> findAnimals(@PathVariable String guardianId);
	
}
