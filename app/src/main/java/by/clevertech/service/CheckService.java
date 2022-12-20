package by.clevertech.service;

import by.clevertech.service.dto.CheckInDto;

/**
 * Business domain main interface
 * 
 * @author Nikita Semeniuk
 *
 */
public interface CheckService {
    public String get(CheckInDto checkInputDto);
}
