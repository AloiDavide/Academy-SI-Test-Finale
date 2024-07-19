package it.testfinale.dao;

import it.testfinale.model.WeatherHourly;
import org.springframework.data.repository.CrudRepository;

public interface WeatherHourlyDao  extends CrudRepository<WeatherHourly, Integer> {
}
