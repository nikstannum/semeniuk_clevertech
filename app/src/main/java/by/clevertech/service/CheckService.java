package by.clevertech.service;

import by.clevertech.data.entity.Check;
import by.clevertech.service.dto.CheckInDto;

public interface CheckService extends CrudService<Check, Long> {
	public Check get(CheckInDto checkInputDto);
}
