import {Component} from '@angular/core';
import {WeatherService} from "../service/weather/weather.service";
import {FormsModule, NgForm} from "@angular/forms";
import {WeatherRequest} from "../../model/weatherRequest";
import {WeatherResponse} from "../../model/weatherResponse";
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {NgFor, NgIf} from "@angular/common";

@Component({
    selector: 'app-forecast',
    standalone: true,
    imports: [FormsModule, NgIf, NgFor],
    templateUrl: './forecast.component.html',
    styleUrl: './forecast.component.css',
    providers: [WeatherService, LocalStorageService]
})
export class ForecastComponent {
    weatherRequest: WeatherRequest = new WeatherRequest(0, 0, "", "");
    weatherResponse: WeatherResponse;
    location: string;

    constructor(private weatherService: WeatherService, public localStorageService: LocalStorageService) {
        if (this.localStorageService.get("weather") != null) {
            this.weatherResponse = JSON.parse(this.localStorageService.get("weather"));
        }

    }

    onSubmit(form: NgForm) {
        if (form.invalid) {
            alert("There was an error in the form.")
            return;
        }

        this.weatherService.getCoordinates(this.location).subscribe({
            next: (coordinateData) => {
                if (coordinateData.results && coordinateData.results.length > 0) {
                    const locationData = coordinateData.results[0];
                    const latitude = locationData.latitude;
                    const longitude = locationData.longitude;

                    console.log(locationData);


                    this.weatherRequest.latitude = latitude;
                    this.weatherRequest.longitude = longitude;


                    console.log(this.weatherRequest);


                    // Call the weather API with updated request
                    this.weatherService.getWeatherData(this.weatherRequest).subscribe({
                        next: (weatherResponse) => {
                            this.weatherResponse = weatherResponse;
                            console.log(this.weatherResponse);
                            this.localStorageService.store("weather", JSON.stringify(weatherResponse))
                        },
                        error: (error) => {
                            console.error('Error fetching weather data:', error);
                            alert('Could not fetch weather data.');
                        }
                    });
                } else {
                    console.error('No results found for the given location.');
                    alert('No results found for the given location.');
                }
            },
            error: (error) => {
                console.error('Error fetching coordinates:', error);
                alert('Could not find the location you entered.');
            }
        });


    }


    onReset(form: NgForm) {
        this.localStorageService.clear();
        form.reset();
    }
}
