package com.petshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.dto.AnimalDto;
import com.petshop.exception.ResponseBody;
import com.petshop.payload.AnimalRequest;
import com.petshop.payload.AnimalResponse;
import com.petshop.service.AnimalService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/animal")
@Tag(name = "Animal")
@OpenAPIDefinition(info = @Info(title = "Animal API", version = "v1.0", description = "Documentation of Animal API"))
public class AnimalController {
	
	@Autowired
	private AnimalService service;
	
	@Value("${server.port}") 
	private String port;
	
	@GetMapping("/status")
	private ResponseEntity<ResponseBody> statusService() {
		String message =  String.format("Serviço ativo e executando na porta %s", port);
		ResponseBody response = new ResponseBody(message);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalResponse>> findAll() {
		List<AnimalDto> dtos = service.findAll();
		
		if (dtos.isEmpty()) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<AnimalResponse> response = dtos.stream()
				.map(dto -> convertDtoToResponse(dto))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnimalResponse> findById(@PathVariable String id){
		Optional<AnimalDto> dto = service.findById(id);
		
		if (dto.isPresent()) {
			return new ResponseEntity<>(convertDtoToResponse(dto.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
	@GetMapping("/{guardianId}/list")
	public ResponseEntity<List<AnimalResponse>> findByGuardian(@PathVariable String guardianId) {
		List<AnimalDto> dtos = service.findByGuardian(guardianId);
		
		if (dtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<AnimalResponse> response = dtos.stream()
				.map(dto -> convertDtoToResponse(dto))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AnimalResponse> createAnimal(@RequestBody @Valid AnimalRequest request) {
		AnimalDto dto = convertRequestToDto(request);
		dto = service.create(dto);
		
		return new ResponseEntity<>(convertDtoToResponse(dto), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AnimalResponse> updateAnimal(@RequestBody @Valid AnimalRequest request, @PathVariable String id) {
		AnimalDto dto = convertRequestToDto(request);
		dto = service.update(id, dto);
		
		return new ResponseEntity<>(convertDtoToResponse(dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnimal(@PathVariable String id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/setDead/{id}")
	public ResponseEntity<Void> setDead(@PathVariable String id) {
		if (service.setDead(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private AnimalDto convertRequestToDto(AnimalRequest request) {
		AnimalDto dto = new AnimalDto();
		dto.setName(request.getName());
		dto.setAge(request.getAge());
		dto.setBreed(request.getBreed());
		dto.setGuardian(request.getGuardian());
		return dto;
	}
	
	private AnimalResponse convertDtoToResponse(AnimalDto dto) {
		AnimalResponse response = new AnimalResponse();
		response.setName(dto.getName());
		response.setAge(dto.getAge());
		response.setBreed(dto.getBreed());
		response.setGuardian(dto.getGuardian());
		response.setId(dto.getId());
		response.setAlive(dto.isAlive());
		return response;
	}

}
