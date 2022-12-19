package by.clevertech.io;

import by.clevertech.service.dto.CheckOutDto;

/**
 * The basic interface that provides the presentation of a {@link CheckOutDto}
 * to the user.
 * 
 * @author Nikita Semeniuk
 *
 */
public interface PrintCheck {

    public void printCheck(String preparedCheck);

}
