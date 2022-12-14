package by.clevertech.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Check {

	private String header;
	private LocalDateTime timestamp;
	private List<CheckItem> products;
	private BigDecimal totalCost;
}
