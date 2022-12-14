package by.clevertech.dao.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CheckItem {
	private Product product;
	private Integer quantity;
	private BigDecimal total;

}
