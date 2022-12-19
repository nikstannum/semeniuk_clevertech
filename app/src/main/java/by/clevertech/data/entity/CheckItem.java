package by.clevertech.data.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * A class that represents an item on a receipt.
 * 
 * @author Nikita Semeniuk
 */
@Data
public class CheckItem {
    private Product product;
    private Integer quantity;
    private BigDecimal total;

}
