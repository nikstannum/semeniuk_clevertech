package by.clevertech.controller.command.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.clevertech.controller.command.Command;
import by.clevertech.data.connection.ConfigManager;
import by.clevertech.service.CheckSerializer;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;
import by.clevertech.service.exception.ClevertechException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileCommand implements Command {
    private final CheckService service;
    private final CheckSerializer serializer;

    @Override
    public void execute(String[] args) {
        CheckInDto in = read(args);
        CheckOutDto out = service.get(in);
        String serialized = serializer.serialize(out);
        write(serialized);
    }

    private CheckInDto read(String[] args) {
        String path = args[0];
        File file = new File(path);
        if (!file.exists() || !file.canRead()) {
            throw new ClevertechException("file is not exists or no readable");
        }
        String content = "";
        try (FileReader reader = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(reader)) {
            content = bufferedReader.readLine();
        } catch (IOException ex) {
            throw new ClevertechException("file read error", ex);
        }
        CheckInDto dto = processContent(content);
        return dto;
    }

    private void write(String preparedCheck) {
        ConfigManager props = ConfigManager.INSTANCE;
        String outputDir = props.getProperty("output.dir");
        String fileName = "result.txt";
        File file = new File(outputDir + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(preparedCheck);
            writer.flush();
        } catch (IOException e) {
            throw new ClevertechException("file write error", e);
        }
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
