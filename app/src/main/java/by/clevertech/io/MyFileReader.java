package by.clevertech.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.clevertech.data.connection.ConfigManager;
import by.clevertech.service.dto.CheckInDto;

public class MyFileReader {

    public CheckInDto getCheckInDto() {
        return readFile();
    }

    private CheckInDto readFile() {
        ConfigManager props = ConfigManager.INSTANCE;
        String path = props.getProperty("input.file");
        File file = new File(path);
        if (!file.exists() || !file.canRead()) {
            throw new RuntimeException("file is not exists or no readable"); // FIXME own exception
        }
        String content = "";
        try (FileReader reader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(reader)) {
            content = bufferedReader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        CheckInDto dto = processContent(content);
        return dto;
    }

    private CheckInDto processContent(String str) {
        CheckInDto dto = new CheckInDto();
        String[] arr = str.split(" ");
        Map<Long, Integer> map = new HashMap<>();
        Long cardId = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("card")) {
                cardId = Long.parseLong(arr[i].substring(5));
            } else {
                String[] productArr = arr[i].split("-");
                map.put(Long.parseLong(productArr[0]), Integer.parseInt(productArr[1]));
            }
        }
        dto.setCardId(cardId);
        dto.setProducts(map);
        return dto;
    }

}
