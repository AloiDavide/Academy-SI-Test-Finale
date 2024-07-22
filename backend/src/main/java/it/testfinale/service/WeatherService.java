package it.testfinale.service;

import it.testfinale.dto.WeatherDto;
import javassist.tools.rmi.ObjectNotFoundException;

public interface WeatherService {

    void saveWeather(WeatherDto WeatherDto, String email) throws ObjectNotFoundException;
}
