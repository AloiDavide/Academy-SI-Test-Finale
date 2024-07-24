import {Component} from '@angular/core';
import {Chart, ChartDataset, ChartOptions, ChartType, registerables} from "chart.js";
import {WeatherService} from "../service/weather/weather.service";
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {Router} from "@angular/router";
import {UserDto} from "../../model/userDto";
import {WeatherResponse} from "../../model/weatherResponse";
import {BaseChartDirective} from "ng2-charts";
import {NgForOf, NgIf} from "@angular/common";

Chart.register(...registerables);
@Component({
    selector: 'app-weather-view',
    standalone: true,
    imports: [
        BaseChartDirective,
        NgForOf,
        NgIf
    ],
    templateUrl: './weather-view.component.html',
    styleUrl: './weather-view.component.css',
    providers: [WeatherService, LocalStorageService, Router]
})
export class WeatherViewComponent {
    weatherResponse:WeatherResponse;

    //Linecharts setup
    public lineChartLabels: string[] = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];
    public lineChartOptions: ChartOptions = {
        responsive: true,
        maintainAspectRatio: false,
    };
    public lineChartType: ChartType = 'line';
    public lineChartLegend: boolean = true;

    public lineChartDataTemp: ChartDataset[] = [
        {
            data: [],
            label: 'Temperature (°C)'
        }
    ];

    public lineChartDataHumidity: ChartDataset[] = [
        {
            data: [],
            label: 'Humidity (%)'
        }
    ];

    public lineChartDataPrecipitation: ChartDataset[] = [
        {
            data: [],
            label: 'Precipitation (mm)'
        }
    ];


    constructor(private weatherService: WeatherService, public localStorageService: LocalStorageService, private router: Router) {
        if (this.localStorageService.get("weather") != null) {
            this.weatherResponse = JSON.parse(this.localStorageService.get("weather"));
            this.updateLinecharts()
        }

    }


    updateLinecharts(): void {
        this.lineChartDataTemp[0]["data"] = this.weatherResponse.hourly.temperature_2m;
        this.lineChartDataHumidity[0]["data"] = this.weatherResponse.hourly.relative_humidity_2m;
        this.lineChartDataPrecipitation[0]["data"] = this.weatherResponse.hourly.precipitation;
    }


    onSave() {
        const user: UserDto = JSON.parse(this.localStorageService.get("loggedUser"));
        const weather: WeatherResponse = JSON.parse(this.localStorageService.get("weather"));

        if (user == null) {
            alert('Please log into your account to access this feature.');
            return;
        }

        this.weatherService.saveToUser(user.mail, weather).subscribe({
            next: res => {
                this.router.navigate(['/saved']);
            },
            error: (error) => {
                console.error('Error saving weather data:', error);
                alert('Could not save the data to your account.');
            }
        });
    }
}
