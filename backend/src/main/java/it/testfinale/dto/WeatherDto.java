package it.testfinale.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDto {

    private double latitude;
    private double longitude;
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    private DailyDto daily;
    private HourlyDto hourly;



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



    public DailyDto getDaily() {
        return daily;
    }

    public void setDaily(DailyDto daily) {
        this.daily = daily;
    }

    public HourlyDto getHourly() {
        return hourly;
    }

    public void setHourly(HourlyDto hourly) {
        this.hourly = hourly;
    }

}