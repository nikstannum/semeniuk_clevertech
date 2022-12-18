/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package by.clevertech;

import by.clevertech.io.ConsoleWriter;
import by.clevertech.io.MyFileReader;
import by.clevertech.service.CheckPreparer;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;
import by.clevertech.service.factory.ServiceFactory;

public class App {

    public static void main(String[] args) throws Exception {

        MyFileReader reader = new MyFileReader();
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
