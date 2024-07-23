package it.testfinale.service;

import it.testfinale.dao.UserDao;
import it.testfinale.dao.WeatherDailyDao;
import it.testfinale.dto.BackendWeatherResponse;
import it.testfinale.dto.HourlyDto;
import it.testfinale.dto.UserDto;
import it.testfinale.dto.WeatherDto;
import it.testfinale.model.User;
import it.testfinale.model.WeatherDaily;
import it.testfinale.model.WeatherHourly;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private UserDao userRepository;

    @Autowired
    private WeatherDailyDao weatherDailyRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private WeatherDailyDao weatherDailyDao;

    public void saveWeather(WeatherDto weatherDto, String email) throws ObjectNotFoundException {

        Optional<User> userOptional = userRepository.findByMail(email);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException("User not found");
        }
        User user = userOptional.get();


        for (String day : weatherDto.getDaily().getTime()) {
            int dayIndex = weatherDto.getDaily().getTime().indexOf(day);

            WeatherDaily wd = new WeatherDaily();
            wd.setUser(user);
            wd.setDay(day);
            wd.setLatitude(weatherDto.getLatitude());
            wd.setLongitude(weatherDto.getLongitude());
            wd.setLocation(weatherDto.getLocation());
            wd.setTimezone(weatherDto.getTimezone());
            wd.setTimezoneAbbreviation(weatherDto.getTimezoneAbbreviation());
            wd.setTemperature2mMax(weatherDto.getDaily().getTemperature2mMax().get(dayIndex));
            wd.setTemperature2mMin(weatherDto.getDaily().getTemperature2mMin().get(dayIndex));
            wd.setPrecipitationSum(weatherDto.getDaily().getPrecipitationSum().get(dayIndex));

            HourlyDto hourlyDto = weatherDto.getHourly();
            
            List<WeatherHourly> weatherHourlies = new ArrayList<>();
            for (int j = 0; j < hourlyDto.getTime().size(); j++) {
                String time = hourlyDto.getTime().get(j);
                if (time.startsWith(day)) {
                    WeatherHourly wh = new WeatherHourly();
                    wh.setDay(wd);
                    wh.setTime(time.substring(time.length() - 5));
                    wh.setTemperature2m(hourlyDto.getTemperature2m().get(j));
                    wh.setRelativeHumidity2m(hourlyDto.getRelativeHumidity2m().get(j));
                    wh.setPrecipitation(hourlyDto.getPrecipitation().get(j));
                    weatherHourlies.add(wh);
                }
            }

            wd.setHours(weatherHourlies);

            //this will also save all the WeatherHourly entities due to cascade settings
            weatherDailyRepository.save(wd);
        }

    }

    @Override
    public List<BackendWeatherResponse> getAllByUser(String email) throws ObjectNotFoundException {
        Optional<User> userOptional = userRepository.findByMail(email);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException("User not found");
        }
        User user = userOptional.get();

        List<WeatherDaily> res = weatherDailyDao.findAllByUser(user);
        return res.stream()
                .map(dailyRecord -> modelMapper.map(dailyRecord, BackendWeatherResponse.class))
                .collect(Collectors.toList());
    }

}
