import { Component } from '@angular/core';
import {NavbarComponent} from "../navbar/navbar.component";
import {ContactsComponent} from "../contacts/contacts.component";
import {CoursesComponent} from "../courses/courses.component";
import {CategoriesComponent} from "../categories/categories.component";
import {HeaderComponent} from "../header/header.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
      HeaderComponent,
      ContactsComponent,
      CoursesComponent,
      CategoriesComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
