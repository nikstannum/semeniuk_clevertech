package by.clevertech.service;

import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;

/**
 * Business domain main interface
 * 
 * @author Nikita Semeniuk
 *
 */
public interface CheckService {
    public CheckOutDto get(CheckInDto checkInputDto);
}
