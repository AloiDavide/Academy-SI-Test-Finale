import {Component} from '@angular/core';
import {WeatherService} from "../service/weather/weather.service";
import {FormsModule, NgForm} from "@angular/forms";
import {WeatherRequest} from "../../model/weatherRequest";
import {WeatherResponse} from "../../model/weatherResponse";
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {NgFor, NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {UserDto} from "../../model/userDto";
import {BaseChartDirective} from "ng2-charts";
import {Chart, registerables, ChartOptions, ChartType, ChartDataset} from 'chart.js';

Chart.register(...registerables);
@Component({
    selector: 'app-forecast',
    standalone: true,
    imports: [FormsModule, NgIf, NgFor, BaseChartDirective],
    templateUrl: './forecast.component.html',
    styleUrl: './forecast.component.css',
    providers: [WeatherService, LocalStorageService, Router]
})
export class ForecastComponent {
    weatherRequest: WeatherRequest = new WeatherRequest(0, 0, "", "");
    weatherResponse: WeatherResponse;
    location: string;


    //Linecharts setup
    public lineChartLabels: string[] = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];
    public lineChartOptions: ChartOptions = {
        responsive: true,
    };
    public lineChartType: ChartType = 'line';
    public lineChartLegend:boolean = true;

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


    updateLinecharts():void{
        this.lineChartDataTemp[0]["data"] = this.weatherResponse.hourly.temperature_2m;
        this.lineChartDataHumidity[0]["data"] = this.weatherResponse.hourly.relative_humidity_2m;
        this.lineChartDataPrecipitation[0]["data"] = this.weatherResponse.hourly.precipitation;
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
                            weatherResponse.location = form.value.location;
                            this.weatherResponse = weatherResponse;

                            console.log(this.weatherResponse);
                            this.localStorageService.store("weather", JSON.stringify(weatherResponse))
                            this.updateLinecharts()
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
