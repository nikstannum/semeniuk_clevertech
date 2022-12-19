package by.clevertech.data.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * A class representing discount cards that are taken into account when
 * calculating the final purchase price.
 * 
 * @author Nikita Semeniuk
 *
 */
@Data
public class DiscountCard {

    private Long cardId;
    private BigDecimal discountSize;
}
