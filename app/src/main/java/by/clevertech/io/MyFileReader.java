package by.clevertech.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import by.clevertech.dao.connection.ConfigManager;
import by.clevertech.service.dto.CheckInputDto;

public class MyFileReader {

	public CheckInputDto getCheckInputDto() {
		return readFile();
	}

	private CheckInputDto readFile() {
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
		CheckInputDto dto = processContent(content);
		return dto;
	}

	private CheckInputDto processContent(String str) {
		CheckInputDto dto = new CheckInputDto();
		String[] arr = str.split(" ");
		Map<Long, Integer> map = new LinkedHashMap<>();
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
