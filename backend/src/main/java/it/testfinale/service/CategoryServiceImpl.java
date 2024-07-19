package it.testfinale.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.testfinale.model.Category;
import it.testfinale.dao.CategoryDao;
import it.testfinale.dto.CategoryDto;
import it.testfinale.exceptions.ObjectNotFoundException;
import it.testfinale.exceptions.UnauthorizedException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<CategoryDto> getAll() {
		List<Category> categories = (List<Category>) categoryDao.findAll();
        return categories.stream()
                    .map(cat -> modelMapper.map(cat, CategoryDto.class))
                    .collect(Collectors.toList());
	}

	@Override
	public void delete(int id) throws ObjectNotFoundException, UnauthorizedException {
		Optional<Category> categoryOptional = categoryDao.findById(id);
		if (!categoryOptional.isEmpty()) {
			Category category = categoryOptional.get();
			if (!category.getCourses().isEmpty()) {
				categoryDao.delete(category);
			}
			else {
				throw new UnauthorizedException();
			}
		}
		else {
			throw new ObjectNotFoundException();
		}
	}



	
}
