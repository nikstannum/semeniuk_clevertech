package by.clevertech.data.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
	private Long id;
	private String name;
	private BigDecimal price;
	boolean discount;
}
