package by.clevertech.service;

import by.clevertech.dao.entity.Check;
import by.clevertech.service.dto.CheckInputDto;

public interface CheckService extends CrudService<Check, Long> {
	public Check get(CheckInputDto checkInputDto);
}
