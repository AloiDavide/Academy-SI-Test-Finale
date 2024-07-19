package it.testfinale.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class WeatherRequest {

    @NotNull(message = "Day is required")
    private String day;

    private Double temperatureMax;

    private Double temperatureMin;

    private Double precipitationTot;




    @NotEmpty
    private List<@Valid HourlyDto> hourly;

    public @NotNull(message = "Day is required") String getDay() {
        return day;
    }

    public void setDay(@NotNull(message = "Day is required") String day) {
        this.day = day;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getPrecipitationTot() {
        return precipitationTot;
    }

    public void setPrecipitationTot(Double precipitationTot) {
        this.precipitationTot = precipitationTot;
    }

    public @NotEmpty List<@Valid HourlyDto> getHourly() {
        return hourly;
    }

    public void setHourly(@NotEmpty List<@Valid HourlyDto> hourly) {
        this.hourly = hourly;
    }
}