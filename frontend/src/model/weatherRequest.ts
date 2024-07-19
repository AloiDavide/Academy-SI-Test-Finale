export class WeatherRequest {
    latitude: number;
    longitude: number;
    timezone:string;
    date:string;

    constructor(latitude: number, longitude: number, timezone: string, date:string) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.date = date;
    }

}
