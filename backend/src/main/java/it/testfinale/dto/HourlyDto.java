package it.testfinale.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.List;

public class HourlyDto {
    @NotNull(message = "Time cannot be null")
    private Time hour;

    private Double temperature;

    private Double humidity;

    private Double precipitation;



    public Time getTime() {
        return hour;
    }

    public void setTime(Time hour) {
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
