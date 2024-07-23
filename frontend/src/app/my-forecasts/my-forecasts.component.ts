import {Component} from '@angular/core';
import {WeatherService} from "../service/weather/weather.service";
import {SavedWeather} from "../../model/savedWeather";
import {UserDto} from "../../model/userDto";
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {WeatherCardComponent} from "../weather-card/weather-card.component";
import {NgFor, NgIf} from "@angular/common";

@Component({
    selector: 'app-my-forecasts',
    standalone: true,
    imports: [WeatherCardComponent, NgFor, NgIf],
    templateUrl: './my-forecasts.component.html',
    styleUrl: './my-forecasts.component.css',
    providers: [WeatherService, LocalStorageService]

})
export class MyForecastsComponent {
    savedWeathers: SavedWeather[];

    constructor(private weatherService: WeatherService, private localStorageService: LocalStorageService) {
        const user: UserDto = JSON.parse(this.localStorageService.get("loggedUser"));

        if (user == null) {
            alert('Please log into your account to access this feature.');
            return;
        }
        this.weatherService.retrieveFromUser(user.mail).subscribe({
            next: result => {
                this.savedWeathers = result;
            },
            error: (error) => {
                console.error('Error retrieving weather data:', error);
                alert('Could not retrieve the data from your account.');
            }
        })

    }
}
