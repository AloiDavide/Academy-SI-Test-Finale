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

	@Column(name = "latitude", nullable = false)
	private double latitude;

	@Column(name = "longitude", nullable = false)
	private double longitude;

	@Column(name = "location")
	private String location;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "timezone_short")
	private String timezoneAbbreviation;

	@Column(name = "temperature_max")
	private double temperature2mMax;

	@Column(name = "temperature_min")
	private double temperature2mMin;

	@Column(name = "precipitation_tot")
	private double precipitationSum;

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

	public double getTemperature2mMax() {
		return temperature2mMax;
	}

	public void setTemperature2mMax(double temperature2mMax) {
		this.temperature2mMax = temperature2mMax;
	}

	public double getTemperature2mMin() {
		return temperature2mMin;
	}

	public void setTemperature2mMin(double temperature2mMin) {
		this.temperature2mMin = temperature2mMin;
	}

	public double getPrecipitationSum() {
		return precipitationSum;
	}

	public void setPrecipitationSum(double precipitationSum) {
		this.precipitationSum = precipitationSum;
	}

	public List<WeatherHourly> getHours() {
		return hours;
	}

	public void setHours(List<WeatherHourly> hours) {
		this.hours = hours;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTimezoneAbbreviation() {
		return timezoneAbbreviation;
	}

	public void setTimezoneAbbreviation(String timezoneAbbreviation) {
		this.timezoneAbbreviation = timezoneAbbreviation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
