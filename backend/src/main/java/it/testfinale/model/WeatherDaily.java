package it.testfinale.model;

import java.util.List;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "weather_daily")
public class WeatherDaily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@Column(name = "day", nullable = false)
	private String day;

	@Column(name = "temperature_max")
	private double temperatureMax;

	@Column(name = "temperature_min")
	private double temperatureMin;

	@Column(name = "precipitation_tot")
	private double precipitationTot;

	@OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WeatherHourly> hours;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public double getPrecipitationTot() {
		return precipitationTot;
	}

	public void setPrecipitationTot(double precipitationTot) {
		this.precipitationTot = precipitationTot;
	}

	public List<WeatherHourly> getHours() {
		return hours;
	}

	public void setHours(List<WeatherHourly> hours) {
		this.hours = hours;
	}


}
