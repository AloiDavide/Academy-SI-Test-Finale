package it.testfinale.dao;

import it.testfinale.model.User;
import it.testfinale.model.WeatherDaily;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherDailyDao  extends CrudRepository<WeatherDaily, Integer> {
    List<WeatherDaily> findAllByUser(User user);
}
