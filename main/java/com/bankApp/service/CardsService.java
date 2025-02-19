package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.CardsDto;
import com.bankApp.entityResponse.CardsResponse;

public interface CardsService {

	CardsResponse addCards(CardsDto dto, Long cid);

	List<CardsResponse> getAllCards();

	CardsResponse getCardsById(Long id);

	CardsResponse updateCards(Long id, CardsDto dto, Long cid);

	void deleteCards(Long id);

}
