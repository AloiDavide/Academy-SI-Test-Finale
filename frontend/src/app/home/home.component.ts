import { Component } from '@angular/core';
import {ContactsComponent} from "../contacts/contacts.component";
import {HeaderComponent} from "../header/header.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
      HeaderComponent,
      ContactsComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
