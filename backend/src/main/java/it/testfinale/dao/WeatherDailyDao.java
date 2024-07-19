package it.testfinale.dao;

import it.testfinale.model.WeatherDaily;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDailyDao  extends CrudRepository<WeatherDaily, Integer> {
}
