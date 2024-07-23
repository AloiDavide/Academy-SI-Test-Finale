import {Component, Input} from '@angular/core';
import {SavedWeather} from "../../model/savedWeather";

@Component({
  selector: 'app-weather-card',
  standalone: true,
  imports: [],
  templateUrl: './weather-card.component.html',
  styleUrl: './weather-card.component.css'
})
export class WeatherCardComponent {
    @Input() forecast!: SavedWeather;

    onExpand() {
        //TODO retrieve the hourly data for that day and show a graph
    }
}
