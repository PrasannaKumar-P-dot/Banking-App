package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Cards;
import com.bankApp.entity.Customer;
import com.bankApp.entityDto.CardsDto;
import com.bankApp.entityResponse.CardsResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.CardsRepository;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.service.CardsService;

@Service
public class CardsServiceImpl implements CardsService{
	
	@Autowired
	CardsRepository cardsRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public CardsResponse addCards(CardsDto dto, Long cid) {
		Customer customer = customerRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id:"+cid));
		
		Cards cards = new Cards();

		cards.setCardType(dto.getCardType());
		cards.setExpiryDate(dto.getExpiryDate());
		cards.setCustomer(customer);
		cardsRepository.save(cards);
		
		CardsResponse cardsResponse = mapper.map(cards, CardsResponse.class);
		return cardsResponse;
	}

	@Override
	public List<CardsResponse> getAllCards() {
		List<Cards> cardss = cardsRepository.findAll();
		List<CardsResponse> cardsResponses = cardss.stream().map(acc -> mapper.map(acc, CardsResponse.class)).collect(Collectors.toList());
		return cardsResponses;
	}

	@Override
	public CardsResponse getCardsById(Long id) {
		Cards cards = cardsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cards not found with Id :"+id));
		CardsResponse cardsResponse = mapper.map(cards, CardsResponse.class);
		return cardsResponse;
	}

	@Override
	public CardsResponse updateCards(Long id, CardsDto dto, Long cid) {
		Cards cards = cardsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cards not found with Id :"+id));

		Customer customer = customerRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id:"+cid));
		
		cards.setCardType(dto.getCardType());
		cards.setExpiryDate(dto.getExpiryDate());
		cards.setCustomer(customer);
		cardsRepository.save(cards);
		CardsResponse cardsResponse = mapper.map(cards, CardsResponse.class);		
		return cardsResponse;
	}

	@Override
	public void deleteCards(Long id) {
		cardsRepository.deleteById(id);
	}

}
