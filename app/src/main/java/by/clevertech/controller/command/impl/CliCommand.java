package by.clevertech.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.controller.command.Command;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliCommand implements Command {
    private final CheckService service;

    @Override
    public void execute(String[] args) {
        CheckInDto in = read(args);
        String out = service.get(in);
        write(out);
    }

    private void write(String out) {
        System.out.println(out);
    }

    private CheckInDto read(String[] args) {
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
