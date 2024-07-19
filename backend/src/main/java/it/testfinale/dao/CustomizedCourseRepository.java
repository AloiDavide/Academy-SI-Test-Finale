package it.testfinale.dao;

import java.util.List;

import it.testfinale.model.Category;
import it.testfinale.model.Course;

public interface CustomizedCourseRepository {
	 
	 public List<Course> findByNameAndCategoryId(String name, Category category);

	}
