package com.petshop.contoller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.dto.PersonDto;
import com.petshop.exception.ResponseBody;
import com.petshop.payload.PersonRequest;
import com.petshop.payload.PersonResponse;
import com.petshop.payload.PersonResponseDetails;
import com.petshop.service.PersonService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person")
@OpenAPIDefinition(info = @Info(title = "Person API", version = "v1.0", description = "Documentation of Person API"))
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Value("${server.port}") 
	private String port;
	
	@GetMapping("/status")
	public ResponseEntity<ResponseBody> statusService() {
        String message =  String.format("Serviço ativo e executando na porta %s", port);
        ResponseBody response = new ResponseBody(message);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid PersonRequest request){
		PersonDto dto = convertRequestToDto(request);
		dto = service.create(dto);
		
		return new ResponseEntity<>(convertDtoToResponse(dto), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<PersonResponse>> findAll(){
		List<PersonDto> people = service.findAll();
		
		if (people.isEmpty()) {
			return ResponseEntity.ok(Collections.emptyList());
		}
		
		List<PersonResponse> response = people.stream()
				.map(person -> convertDtoToResponse(person))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonResponseDetails> findPersonById(@PathVariable String id) {
		PersonDto person = service.findPersonById(id);
		
		return new ResponseEntity<>(convertDtoToResponseDetails(person) ,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonResponse> updatePerson(@PathVariable String id, @RequestBody @Valid PersonRequest request) {
		PersonDto dto = convertRequestToDto(request);
		dto = service.update(id, dto);
		
		return new ResponseEntity<>(convertDtoToResponse(dto), HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable String id){
		service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private PersonDto convertRequestToDto(PersonRequest request) {
		PersonDto dto = new PersonDto();
		dto.setName(request.getName());
		dto.setCpf(request.getCpf());
		dto.setPhone(request.getPhone());
		return dto;
	}
	
	private PersonResponse convertDtoToResponse(PersonDto dto) {
		PersonResponse response = new PersonResponse();
		response.setId(dto.getId());
		response.setName(dto.getName());
		response.setCpf(dto.getCpf());
		response.setPhone(dto.getPhone());
		return response;
	}
	
	private PersonResponseDetails convertDtoToResponseDetails(PersonDto dto) {
		PersonResponseDetails response = new PersonResponseDetails();
		response.setId(dto.getId());
		response.setName(dto.getName());
		response.setCpf(dto.getCpf());
		response.setPhone(dto.getPhone());
		response.setAnimals(dto.getAnimals());
		return response;
	}

}
