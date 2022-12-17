package by.clevertech.io;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import by.clevertech.dao.entity.Check;
import by.clevertech.dao.entity.CheckItem;

public class ConsoleWriter implements PrintCheck {

	@Override
	public void printCheck(Check check) {
		String header = prepareHeader();
		StringBuilder stringBuilder = new StringBuilder(header);
		List<CheckItem> items = check.getProducts();
		for (CheckItem item : items) {
			Integer quantity = item.getQuantity();
			String productName = item.getProduct().getName();
			BigDecimal productPrice = item.getProduct().getPrice();
			BigDecimal total = item.getTotal();
		}

	}

	private String prepareHeader() {
		String horizonLine = "+" + StringUtils.center("", 40, "-") + "+\n";
		String vertLineStart = "| ";
		String vertLineEnd = " |\n";
		String head = vertLineStart + StringUtils.center("CASH RECEIPT", 38) + vertLineEnd;
		String storeName = vertLineStart + StringUtils.center("SUPERMARKET 123", 38) + vertLineEnd;
		String address = vertLineStart + StringUtils.center("12, MILKYWAY Galaxy/Earth", 38) + vertLineEnd;
		String tel = vertLineStart + StringUtils.center("Tel: 123-456-7890", 38) + vertLineEnd;
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = dateTime.toLocalDate();
		LocalTime time = dateTime.toLocalTime();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(dateFormat);
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = time.format(timeFormat);
		String cashierName = "CASHIER: 1234";
		String cashierAndDate = vertLineStart + StringUtils.rightPad(cashierName, 38 - cashierName.length())
						+ StringUtils.leftPad(formattedDate.toString(), cashierName.length()) + vertLineEnd;
		String timeStr = vertLineStart + StringUtils.leftPad(formattedTime, 38) + vertLineEnd;

//		String prepareTableHead = StringUtils.center("QTY", 3) + StringUtils.rightPad("DESCRIPTION", 16)
//						+ StringUtils.center("PRICE", 8) + StringUtils.center("TOTAL", 8);
//		String tableHead = vertLineStart + prepareTableHead + vertLineEnd;
		String qty = StringUtils.rightPad("QTY", 3);
		String descr = StringUtils.rightPad("DESCRIPTION", 19);
		String price = StringUtils.center("PRICE", 7);
		String total = StringUtils.center("TOTAL", 7);
		String tableHead = vertLineStart + qty + "  " + descr + price + total + vertLineEnd;
		StringBuilder res = new StringBuilder(horizonLine).append(head).append(storeName).append(address).append(tel)
						.append(cashierAndDate).append(timeStr).append(horizonLine).append(tableHead);
		
		
		return null;
	}

}
