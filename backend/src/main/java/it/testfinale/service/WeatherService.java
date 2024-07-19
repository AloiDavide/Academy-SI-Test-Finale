package it.testfinale.service;

import it.testfinale.dto.WeatherRequest;
import javassist.tools.rmi.ObjectNotFoundException;

public interface WeatherService {

    void saveWeather(WeatherRequest weatherRequest, String email) throws ObjectNotFoundException;
}
