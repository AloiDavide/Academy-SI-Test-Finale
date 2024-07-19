package it.testfinale.service;

import java.util.List;
import java.util.NoSuchElementException;

import it.testfinale.dto.UserDto;
import it.testfinale.dto.UserLoginRequestDto;
import it.testfinale.dto.UserSignupDto;
import it.testfinale.dto.UserUpdateDto;
import it.testfinale.exceptions.AreadySubscribedException;
import it.testfinale.exceptions.ObjectNotFoundException;
import it.testfinale.model.User;

public interface UserService {
	User getUserByMail(String email) throws ObjectNotFoundException; 
	UserDto getUserDtoByMail(String email) throws ObjectNotFoundException;
	List<UserDto> getUsers();
	void updateUserData(UserUpdateDto userUpdateDto);
	void deleteUser(String email);
	boolean existsUserByEmail(String email);
	boolean login(UserLoginRequestDto userLoginRequestDto);
	void userSignup(UserSignupDto userSignupDto);
	void subscribeToCourse(String email, int courseId) throws NoSuchElementException, AreadySubscribedException;
	void unsubscribeFromCourse(String email, int courseId);
}
