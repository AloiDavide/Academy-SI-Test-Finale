import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {ForecastComponent} from "./forecast/forecast.component";
import {MyForecastsComponent} from "./my-forecasts/my-forecasts.component";

export const routes: Routes = [
    {path:'', component:HomeComponent},
    {path:'login', component:LoginComponent},
    {path:'register', component:RegisterComponent},
    {path:'forecast', component:ForecastComponent},
    {path:'saved', component:MyForecastsComponent}
];




