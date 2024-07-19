import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {WeatherRequest} from "../../../model/weatherRequest";
import {Observable} from "rxjs";
import {WeatherResponse} from "../../../model/weatherResponse";

@Injectable({
    providedIn: 'root'
})
export class WeatherService {

    private weatherApi = 'https://api.open-meteo.com/v1/forecast';
    private geocodingApi = 'https://geocoding-api.open-meteo.com/v1/search';

    constructor(private http: HttpClient) {
    }


    getWeatherData(weatherRequest: WeatherRequest): Observable<WeatherResponse> {
        const params = {
            latitude: weatherRequest.latitude.toString(),
            longitude: weatherRequest.longitude.toString(),
            daily: 'temperature_2m_max,temperature_2m_min,precipitation_sum',
            hourly: 'temperature_2m,relative_humidity_2m,precipitation',
            timezone: "auto",
            start_date: weatherRequest.date,
            end_date: weatherRequest.date,
        };

        console.log(params);

        return this.http.get<WeatherResponse>(this.weatherApi, {params});
    }


    getCoordinates(location: string): Observable<any> {

        const params = {name: location};
        return this.http.get<any>(this.geocodingApi+"?name="+location, {params});
    }

}
