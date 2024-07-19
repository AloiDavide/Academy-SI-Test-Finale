import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-contacts',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent {
    contactForm = {
    name: '',
    email: '',
    message: ''
  };

  onSubmit() {
    console.log('Contact Form Submitted', this.contactForm);
  }
}
