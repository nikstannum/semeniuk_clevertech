package by.clevertech.dao.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DiscountCard {

	private Long cardId;
	private BigDecimal discountSize;
}
