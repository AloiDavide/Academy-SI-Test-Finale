package it.testfinale.service;

import it.testfinale.dao.UserDao;
import it.testfinale.dao.WeatherDailyDao;
import it.testfinale.dto.WeatherRequest;
import it.testfinale.model.User;
import it.testfinale.model.WeatherDaily;
import it.testfinale.model.WeatherHourly;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private UserDao userRepository;

    @Autowired
    private WeatherDailyDao weatherDailyRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public void saveWeather(WeatherRequest weatherRequest, String email) throws ObjectNotFoundException {

        Optional<User> userOptional = userRepository.findByMail(email);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException("User not found");
        }
        User user = userOptional.get();

        WeatherDaily daily = modelMapper.map(weatherRequest, WeatherDaily.class);


//
//        // Create WeatherHourly entities from the hourly data in WeatherRequest
//        List<WeatherHourly> weatherHourlies = weatherRequest.getHourly().stream().map(hourlyDto -> {
//            WeatherHourly weatherHourly = new WeatherHourly();
//            weatherHourly.setWeatherDaily(weatherDaily);
//            weatherHourly.setTime(hourlyDto.getTime());
//            weatherHourly.setTemperature(hourlyDto.getTemperature());
//            weatherHourly.setHumidity(hourlyDto.getHumidity());
//            weatherHourly.setPrecipitation(hourlyDto.getPrecipitation());
//            return weatherHourly;
//        }).toList();
//
//        weatherDaily.setWeatherHourlies(weatherHourlies);

        // Save the WeatherDaily entity (which will also save the WeatherHourly entities due to cascade settings)
        weatherDailyRepository.save(daily);
    }
}
