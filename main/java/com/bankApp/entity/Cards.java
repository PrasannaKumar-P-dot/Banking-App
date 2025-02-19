package com.bankApp.entity;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cards {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	private String cardType;
	private Date expiryDate;
	
	private String cardNumber;
	
	@PrePersist
	private void generateCardNumber() {
		if(this.cardNumber == null) {
			this.cardNumber = generatedRandomNumber();
		}
	}
	
	private String generatedRandomNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4000"); // Start with "4000" (Visa) or "5000" (MasterCard)
        for (int i = 0; i < 12; i++) {
            cardNumber.append(random.nextInt(10)); // Append 12 random digits
        }
        return cardNumber.toString();
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
