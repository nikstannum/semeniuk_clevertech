package by.clevertech.service.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CheckInDto {
	private Map<Long, Integer> products;
	private Long cardId;
}
