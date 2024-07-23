package it.testfinale.service;

import it.testfinale.dto.BackendWeatherResponse;
import it.testfinale.dto.WeatherDto;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface WeatherService {

    void saveWeather(WeatherDto WeatherDto, String email) throws ObjectNotFoundException;

    List<BackendWeatherResponse> getAllByUser(String email) throws ObjectNotFoundException;
}
