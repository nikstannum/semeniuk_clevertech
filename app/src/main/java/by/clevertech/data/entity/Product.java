package by.clevertech.data.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Class representing the item in the receipt.
 * 
 * @author Nikita Semeniuk
 *
 */
@Data
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    boolean discount;
}
