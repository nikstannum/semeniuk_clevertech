package by.clevertech.service;

import by.clevertech.service.dto.CheckOutDto;

/**
 * interface for {@link CheckOutDto} serialization
 * 
 * @author Nikita Semeniuk
 *
 */
public interface CheckSerializer {
    public <T> T serialize(CheckOutDto dto);

}
