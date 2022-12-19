package by.clevertech.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import by.clevertech.data.entity.CheckItem;
import lombok.Data;

/**
 * An entity that represents the output as the result of processing the input
 * parameters.
 * 
 * @author Nikita Semeniuk
 *
 */
@Data
public class CheckOutDto {

    private String header;
    private LocalDateTime timestamp;
    private List<CheckItem> items;
    private BigDecimal totalCost;
    private BigDecimal fullCost;
}
