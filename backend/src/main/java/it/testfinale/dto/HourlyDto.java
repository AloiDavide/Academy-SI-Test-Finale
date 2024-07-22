package it.testfinale.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.List;

public class HourlyDto {
    @JsonProperty("temperature_2m")
    private List<Double> temperature2m;
    @JsonProperty("relative_humidity_2m")
    private List<Double> relativeHumidity2m;
    private List<Double> precipitation;
    private List<String> time;



    public List<Double> getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(List<Double> temperature2m) {
        this.temperature2m = temperature2m;
    }

    public List<Double> getRelativeHumidity2m() {
        return relativeHumidity2m;
    }

    public void setRelativeHumidity2m(List<Double> relativeHumidity2m) {
        this.relativeHumidity2m = relativeHumidity2m;
    }

    public List<Double> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

}
