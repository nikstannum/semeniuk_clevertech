package by.clevertech.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

import by.clevertech.controller.command.Command;
import by.clevertech.service.CheckSerializer;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;
import lombok.RequiredArgsConstructor;

/**
 * Implements {@link Command}
 * <p>
 * The class provides user interaction through the command line
 * 
 * @author Nikita Semeniuk
 *
 */
@RequiredArgsConstructor
public class CliCommand implements Command {
    private final CheckService service;
    private final CheckSerializer serializer;

    /**
     * command execution method
     */
    @Override
    public void execute(String[] args) {
        CheckInDto in = read(args);
        CheckOutDto out = service.get(in);
        String serialized = serializer.serialize(out);
        write(serialized);
    }

    /**
     * outputs {@link CheckOutDto} to standard output stream
     * 
     * @param out the receipt prepared for printing
     */

    private void write(String out) {
        System.out.println(out);
    }

    /**
     * reads the contents of the command line arguments
     * 
     * @param args command line arguments
     * @return new a {@link CheckInDto} containing a map of data about products (id,
     *         quantity) and a discount card (id)
     */
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
