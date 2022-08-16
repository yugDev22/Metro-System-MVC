package com.metro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private String transactionId;
	private Integer cardId;
	private Integer boardingStationId;
	private Integer destinationStationId;
	private Double fare;
	private LocalDateTime swipeInTime;
	private LocalDateTime swipeOutTime;

}
