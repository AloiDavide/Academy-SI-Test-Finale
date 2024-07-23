package it.testfinale.model;

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

	@Column(name = "hour", nullable = false)
	private String time;

	@Column(name = "temperature")
	private Double temperature2m;

	@Column(name = "humidity")
	private Double relativeHumidity2m;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getTemperature2m() {
		return temperature2m;
	}

	public void setTemperature2m(Double temperature2m) {
		this.temperature2m = temperature2m;
	}

	public Double getRelativeHumidity2m() {
		return relativeHumidity2m;
	}

	public void setRelativeHumidity2m(Double relativeHumidity2m) {
		this.relativeHumidity2m = relativeHumidity2m;
	}

	public Double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}
}
