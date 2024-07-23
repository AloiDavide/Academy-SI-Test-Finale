package it.testfinale.dto;

public class BackendWeatherResponse {
    private String day;
    private String location;
    private String timezoneAbbreviation;
    private double temperature2mMax;
    private double temperature2mMin;
    private double precipitationSum;

    // Getters and Setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
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
}
