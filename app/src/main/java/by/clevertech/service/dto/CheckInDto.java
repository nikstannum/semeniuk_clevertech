package by.clevertech.service.dto;

import java.util.Map;

import lombok.Data;

/**
 * An entity representing the input data
 * 
 * @author Nikita Semeniuk
 *
 */
@Data
public class CheckInDto {
    private Map<Long, Integer> products;
    private Long cardId;
}
