package it.testfinale.dao;

import org.springframework.data.repository.CrudRepository;

import it.testfinale.model.Category;

public interface CategoryDao extends CrudRepository<Category, Integer>{

}
