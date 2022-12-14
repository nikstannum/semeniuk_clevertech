package by.clevertech.controller;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.service.dto.CheckInputDto;

public class CliReader {

	public void read(String[] args) {
		CheckInputDto checkInputDto = new CheckInputDto();
		Map<Long, Integer> items = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].contains("card")) {
				String cardIdStr = args[i].split("-")[1];
				Long cardId = Long.parseLong(cardIdStr);
				checkInputDto.setCardId(cardId);
			} else {
				String[] itemArr = args[i].split("-");
				Long productId = Long.parseLong(itemArr[0]);
				Integer quantity = Integer.parseInt(itemArr[1]);
				items.put(productId, quantity);
				checkInputDto.setProducts(items);
			}
		}
	}

}
