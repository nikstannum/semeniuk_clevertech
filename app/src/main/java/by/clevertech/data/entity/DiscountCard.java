package by.clevertech.data.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DiscountCard {

	private Long cardId;
	private BigDecimal discountSize;
}
