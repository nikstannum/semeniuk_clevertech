package by.clevertech.service;

import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;

public interface CheckService {
    public CheckOutDto get(CheckInDto checkInputDto);
}
