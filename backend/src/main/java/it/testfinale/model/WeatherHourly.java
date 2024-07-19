package it.testfinale.model;

import java.sql.Time;

import jakarta.persistence.*;



@Entity
@Table(name = "weather_hourly")
public class WeatherHourly {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "day_id", nullable = false)
	private WeatherDaily day;

	@Temporal(TemporalType.TIME)
	@Column(name = "hour", nullable = false)
	private Time hour;

	@Column(name = "temperature")
	private Double temperature;

	@Column(name = "humidity")
	private Double humidity;

	@Column(name = "precipitation")
	private Double precipitation;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WeatherDaily getDay() {
		return day;
	}

	public void setDay(WeatherDaily day) {
		this.day = day;
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}
}
