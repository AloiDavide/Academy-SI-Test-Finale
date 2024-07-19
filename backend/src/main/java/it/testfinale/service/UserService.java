package it.testfinale.service;

import java.util.List;
import java.util.NoSuchElementException;

import it.testfinale.dto.UserDto;
import it.testfinale.dto.UserLoginRequestDto;
import it.testfinale.dto.UserSignupDto;
import it.testfinale.exceptions.ObjectNotFoundException;
import it.testfinale.model.User;

public interface UserService {
	User getUserByMail(String email) throws ObjectNotFoundException; 
	UserDto getUserDtoByMail(String email) throws ObjectNotFoundException;
	List<UserDto> getUsers();
	void deleteUser(String email);
	boolean existsUserByEmail(String email);
	boolean login(UserLoginRequestDto userLoginRequestDto);
	void userSignup(UserSignupDto userSignupDto);
}
