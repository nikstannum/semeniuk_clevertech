/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package by.clevertech;

import by.clevertech.io.ClevertechFileReader;
import by.clevertech.io.ConsoleWriter;
import by.clevertech.service.CheckPreparer;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;
import by.clevertech.service.factory.ServiceFactory;
import lombok.extern.log4j.Log4j2;

/**
 * The App class is a class that contains the main method and implements a
 * simple application that receives a set of parameters (product IDs and a
 * discount card ID) as command line arguments or as a file and, based on these
 * data, returns the generated {@link CheckOutDto} to the standard output stream
 * or as txt file. The directory where the txt file will be placed is specified
 * in the properties file.
 * 
 * @author Nikita Semeniuk
 *
 */
@Log4j2
public class App {

    public static void main(String[] args) throws Exception {

        ClevertechFileReader reader = new ClevertechFileReader();
        CheckInDto dto = reader.getCheckInDto();
        CheckService service = ServiceFactory.INSTANCE.getService(CheckService.class);
        CheckOutDto rawCheck = service.get(dto);
        ConsoleWriter writer = new ConsoleWriter();
//        MyFileWriter writer = new MyFileWriter();
        CheckPreparer preparer = new CheckPreparer();
        String check = preparer.prepareCheck(rawCheck);
        writer.printCheck(check);

    }
}
