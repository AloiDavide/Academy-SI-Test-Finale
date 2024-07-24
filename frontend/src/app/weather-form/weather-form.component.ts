import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {WeatherService} from "../service/weather/weather.service";
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {WeatherRequest} from "../../model/weatherRequest";

@Component({
  selector: 'app-weather-form',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './weather-form.component.html',
  styleUrl: './weather-form.component.css',
    providers: [WeatherService, LocalStorageService]
})
export class WeatherFormComponent {
    weatherRequest: WeatherRequest = new WeatherRequest(0, 0, "", "");
    city: string;

    @Output()
    forecastEvent: EventEmitter<void> = new EventEmitter<void>();
    constructor(private weatherService: WeatherService, private localStorageService: LocalStorageService) {

    }

    onSubmit(form: NgForm) {
        if (form.invalid) {
            alert("There was an error in the form.")
            return;
        }

        this.weatherService.getCoordinates(this.city).subscribe({
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
                            weatherResponse.location = this.city;

                            console.log(weatherResponse);
                            this.localStorageService.store("weather", JSON.stringify(weatherResponse))

                            //Notify the parent component
                            this.forecastEvent.emit();

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
        location.reload();
    }
}
