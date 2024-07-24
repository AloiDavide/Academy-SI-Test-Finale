import {Component} from '@angular/core';
import {WeatherFormComponent} from "../weather-form/weather-form.component";
import {WeatherViewComponent} from "../weather-view/weather-view.component";


@Component({
    selector: 'app-forecast',
    standalone: true,
    imports: [WeatherFormComponent, WeatherViewComponent],
    templateUrl: './forecast.component.html',
    styleUrl: './forecast.component.css'
})
export class ForecastComponent {

    onForecastEvent() {
        //refresh the page to make WeatherViewComponent pull the changes from local storage
        //location.reload()
    }
}
