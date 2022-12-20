package by.clevertech.service;

import by.clevertech.service.dto.CheckOutDto;

public interface CheckSerializer {
    public <T> T serialize(CheckOutDto dto);

}
