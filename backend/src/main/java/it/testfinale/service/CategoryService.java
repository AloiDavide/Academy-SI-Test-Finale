package it.testfinale.service;

import java.util.List;

import it.testfinale.dto.CategoryDto;
import it.testfinale.exceptions.ObjectNotFoundException;
import it.testfinale.exceptions.UnauthorizedException;

public interface CategoryService {
	List<CategoryDto> getAll();
	
	void delete(int id) throws ObjectNotFoundException, UnauthorizedException ;

	
	
}
