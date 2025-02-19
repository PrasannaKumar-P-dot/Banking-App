package com.bankApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entityDto.CardsDto;
import com.bankApp.entityResponse.CardsResponse;
import com.bankApp.service.CardsService;

@RestController
@RequestMapping("/api/cards")
public class CardsController {
	
	@Autowired
	private CardsService service;
	
	public void setService(CardsService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/{cid}")
	public CardsResponse addCards(@RequestBody CardsDto Dto, @PathVariable(value = "cid") Long cid) {
		return service.addCards(Dto, cid);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/all")
	public List<CardsResponse> getAllCards() {
		return service.getAllCards();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public CardsResponse getCardsById(@PathVariable(value = "id") Long id) {
		return service.getCardsById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}/{cid}")
	public CardsResponse updateCards(@PathVariable(value = "id") Long id, @RequestBody CardsDto dto, @PathVariable(value = "cid") Long cid) {
		return service.updateCards(id,dto, cid);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCards(@PathVariable(value = "id") Long id) {
		service.deleteCards(id);
		return ResponseEntity.ok("Card deleted Successfully");
	}
}
