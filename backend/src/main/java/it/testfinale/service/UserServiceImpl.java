package it.testfinale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.testfinale.dao.UserDao;
import it.testfinale.dto.UserDto;
import it.testfinale.dto.UserLoginRequestDto;
import it.testfinale.dto.UserSignupDto;
import it.testfinale.model.WeatherHourly;
import it.testfinale.model.User;
import it.testfinale.exceptions.ObjectNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	private ModelMapper modelMapper = new ModelMapper();
	

	private UserDto userToDto(User user) {
	    UserDto userDto = modelMapper.map(user, UserDto.class);

		    
	    return userDto;
	}	

	@Override
	public void userSignup(UserSignupDto userSignupDto) throws NoSuchElementException {
		userSignupDto.setPassword(DigestUtils.sha256Hex(userSignupDto.getPassword()));
		User newUser = modelMapper.map(userSignupDto, User.class);

		newUser.setMail(userSignupDto.getMail());
		System.out.println(newUser);
		userDao.save(newUser);
	}


	@Override
	public void deleteUser(String email) {
		
		Optional<User> optional = userDao.findByMail(email);
		
		if(optional.isPresent())
			userDao.delete(optional.get());
		
	}

	@Override
	public User getUserByMail(String email) throws ObjectNotFoundException {
		
		Optional<User> optional = userDao.findByMail(email);
		
		if(optional.isPresent()) {
			return optional.get();
		}
	        
		
		throw new ObjectNotFoundException();
	}
	


	
	@Override
	public UserDto getUserDtoByMail(String email) throws ObjectNotFoundException  {
		
		Optional<User> optional = userDao.findByMail(email);
		
		if(optional.isPresent()) {
			User user = optional.get();
	        return modelMapper.map(user, UserDto.class);
		}
		
		throw new ObjectNotFoundException();
	}
	
	@Override
	public List<UserDto> getUsers() {
		List<User> users = (List<User>) userDao.findAll();
        return users.stream()
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .collect(Collectors.toList()
                    );
    }


	@Override
	public boolean existsUserByEmail(String email) {
		return userDao.existsByMail(email);
		
	}

	@Override
	public boolean login(UserLoginRequestDto userLoginRequestDto) {
		
		String encrypted = DigestUtils.sha256Hex(userLoginRequestDto.getPassword());
		
		Optional<User> optional = userDao.findByMailAndPassword(userLoginRequestDto.getEmail(), encrypted);

		if(optional.isPresent()) 
			return true;
		else
			return false;
	}

}
