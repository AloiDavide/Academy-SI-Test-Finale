package it.testfinale.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.testfinale.model.Category;
import it.testfinale.model.Course;

public interface CourseDao  extends CrudRepository<Course, Integer>, CustomizedCourseRepository{
	List<Course> findByNameAndShortDescriptionAndCategory(String name, String shortDescription, Category category);

	List<Course> findCourseByCategory(Category category);
	
}
