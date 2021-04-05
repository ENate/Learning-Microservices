package com.learning.microservices.paymentservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PAYMENT_TB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	// Define fields
	private int paymentId;
	private String paymentStatus;
	private String transactionId;

}
