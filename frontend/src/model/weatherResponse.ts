export class WeatherResponse {
    latitude: number;
    longitude: number;
    timezone: string;
    timezone_abbreviation: string;
    utc_offset_seconds: number;

    daily: {
        temperature_2m_max: number[];
        temperature_2m_min: number[];
        precipitation_sum: number[];
        time: string[];
    };

    hourly: {
        temperature_2m: number[];
        relative_humidity_2m: number[];
        precipitation: number[];
        time: string[];
    };
}
