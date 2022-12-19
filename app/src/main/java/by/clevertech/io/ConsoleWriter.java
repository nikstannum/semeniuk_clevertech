package by.clevertech.io;

import by.clevertech.service.dto.CheckOutDto;

/**
 * Implements {@link PrintCheck}
 * <p>
 * A class that provides output of a {@link CheckOutDto} to the console.
 * 
 * @author Nikita Semeniuk
 *
 */
public class ConsoleWriter implements PrintCheck {

    @Override
    public void printCheck(String preparedCheck) {
        System.out.println(preparedCheck);
    }

}
