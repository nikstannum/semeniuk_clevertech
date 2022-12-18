package by.clevertech.io;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.service.dto.CheckInDto;

public class CliReader {

    public CheckInDto getCheckInDto(String[] args) {
        CheckInDto dto = new CheckInDto();
        Map<Long, Integer> map = new HashMap<>();
        Long cardId = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("card")) {
                cardId = Long.parseLong(args[i].substring(5));
            } else {
                String[] productArr = args[i].split("-");
                map.put(Long.parseLong(productArr[0]), Integer.parseInt(productArr[1]));
            }
        }
        dto.setCardId(cardId);
        dto.setProducts(map);
        return dto;
    }
}
