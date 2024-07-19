package it.testfinale.service;
import java.util.Collection;
import java.util.List;

import it.testfinale.dto.CourseDto;
import it.testfinale.dto.CourseUpdateDto;
import it.testfinale.model.Category;
import it.testfinale.model.Course;

public interface CourseService {
	Course getCourseById(int id); 
	List<CourseDto> getCourses();
	Course createCourse(CourseDto course);
	void updateCourse(CourseUpdateDto corso);
	void deleteCourse(int id);
	CourseDto getCourseDTOById(int id);
	Collection<CourseDto> searchByNameAndCategoryId(String name, Category category);
	List<CourseDto> findByCategory(int id);
	void deleteByCategory(int id);
	
}
