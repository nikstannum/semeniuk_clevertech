package by.clevertech.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import by.clevertech.data.entity.CheckItem;
import by.clevertech.service.CheckSerializer;
import by.clevertech.service.dto.CheckOutDto;

/**
 * A class that prepares a {@link CheckOutDto} before sending it to the user.
 * 
 * @author Nikita Semeniuk
 *
 */
public class CheckStringSerializer implements CheckSerializer {

    private static final int FIELD_WIDTH = 40;
    private static final int STRING_LENGTH = 38;
    private static final String VERT_LINE_START = "| ";
    private static final String VERT_LINE_END = " |\n";
    private static final String HORIZON_LINE = "+" + StringUtils.center("", FIELD_WIDTH, "-") + "+\n";

    /**
     * serializes a {@link CheckOutDto} to a string
     */
    @SuppressWarnings("unchecked")
    @Override
    public String serialize(CheckOutDto dto) {
        StringBuilder result = new StringBuilder();
        return result.append(addHeader()).append(addItems(dto)).append(addFooter(dto)).toString();
    }

    /**
     * adds items to the receipt
     * 
     * @param checkOutDto the serializable object
     * @return string representation of a {@link CheckOutDto} with commodity items
     */
    private StringBuilder addItems(CheckOutDto checkOutDto) {
        StringBuilder result = new StringBuilder();
        List<CheckItem> items = checkOutDto.getItems();
        for (CheckItem item : items) {
            Integer quantity = item.getQuantity();
            String productName = item.getProduct().getName();
            BigDecimal productPrice = item.getProduct().getPrice();
            BigDecimal total = item.getTotal();
            result.append(VERT_LINE_START).append(StringUtils.center(quantity.toString(), 3)).append("  ")
                    .append(StringUtils.rightPad(productName, 17))
                    .append(StringUtils.center("$" + productPrice.toString(), 8))
                    .append(StringUtils.center("$" + total.toString(), 8)).append(VERT_LINE_END);
        }
        return result;
    }

    /**
     * adds header to the receipt
     * 
     * @return string representation of a {@link CheckOutDto} with the header
     */
    private StringBuilder addHeader() {
        String head = VERT_LINE_START + StringUtils.center("CASH RECEIPT", STRING_LENGTH) + VERT_LINE_END;
        String storeName = VERT_LINE_START + StringUtils.center("SUPERMARKET 123", STRING_LENGTH) + VERT_LINE_END;
        String address = VERT_LINE_START + StringUtils.center("12, MILKYWAY Galaxy/Earth", STRING_LENGTH)
                + VERT_LINE_END;
        String tel = VERT_LINE_START + StringUtils.center("Tel: 123-456-7890", STRING_LENGTH) + VERT_LINE_END;
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateFormat);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormat);
        String cashierName = "CASHIER: 1234";
        String cashierAndDate = VERT_LINE_START + cashierName
                + StringUtils.leftPad(formattedDate.toString(), STRING_LENGTH - cashierName.length()) + VERT_LINE_END;
        String timeStr = VERT_LINE_START + StringUtils.leftPad(formattedTime, STRING_LENGTH) + VERT_LINE_END;

        String qty = StringUtils.rightPad("QTY", 3);
        String descr = StringUtils.rightPad("DESCRIPTION", 17);
        String price = StringUtils.center("PRICE", 8);
        String total = StringUtils.center("TOTAL", 8);
        String tableHead = VERT_LINE_START + qty + "  " + descr + price + total + VERT_LINE_END;
        StringBuilder result = new StringBuilder(HORIZON_LINE).append(head).append(storeName).append(address)
                .append(tel).append(cashierAndDate).append(timeStr).append(HORIZON_LINE).append(tableHead);
        return result;
    }

    /**
     * adds a footer to the receipt including the full cost, the discount amount and
     * the final cost
     * 
     * @param dto the serializable object
     * @return string representation of a {@link CheckOutDto} with the footer
     */
    private StringBuilder addFooter(CheckOutDto dto) {
        StringBuilder result = new StringBuilder();
        String doubleHorizonLine = "+" + StringUtils.center("", FIELD_WIDTH, "=") + "+\n";
        result.append(doubleHorizonLine);
        BigDecimal fullCost = dto.getFullCost();
        String taxable = "TAXABLE TOT.";

        String fullCostStr = VERT_LINE_START + taxable
                + StringUtils.leftPad("$" + fullCost.toString(), STRING_LENGTH - taxable.length()) + VERT_LINE_END;

        result.append(fullCostStr);
        BigDecimal totalCost = dto.getTotalCost();
        String discount = fullCost.subtract(totalCost).toString();
        String disc = "DISCOUNT";
        String discountStr = VERT_LINE_START + disc + StringUtils.leftPad("$" + discount, STRING_LENGTH - disc.length())
                + VERT_LINE_END;
        result.append(discountStr);
        String total = "TOTAL";
        String totalStr = VERT_LINE_START + total
                + StringUtils.leftPad("$" + totalCost.toString(), STRING_LENGTH - total.length()) + VERT_LINE_END;

        result.append(totalStr).append(HORIZON_LINE);
        return result;
    }
}
