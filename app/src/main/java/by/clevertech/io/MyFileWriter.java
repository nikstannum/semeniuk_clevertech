package by.clevertech.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import by.clevertech.data.connection.ConfigManager;

public class MyFileWriter implements PrintCheck {

    @Override
    public void printCheck(String preparedCheck) {
        ConfigManager props = ConfigManager.INSTANCE;
        String outputDir = props.getProperty("output.dir");
        String fileName = "result.txt";
        File file = new File(outputDir + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(preparedCheck);
            writer.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
