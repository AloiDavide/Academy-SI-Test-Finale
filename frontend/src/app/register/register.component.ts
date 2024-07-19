import {Component, EventEmitter} from '@angular/core';
import {RegisterRequest} from "../../model/registerRequest";
import {FormsModule, NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../service/user/user.service";

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [
        FormsModule
    ],
    templateUrl: './register.component.html',
    styleUrl: './register.component.css'
})
export class RegisterComponent {
    registerRequest: RegisterRequest = new RegisterRequest("", "", "");



    constructor(private userService: UserService, public router: Router) {

    }


    onSubmit(registerForm: NgForm) {
        console.log(registerForm);
        if (registerForm.valid) {
            console.log(this.registerRequest);

            //Rest call to the backend to register a user
            this.userService.register(this.registerRequest).subscribe({
                next: (result) => {
                    // On successful call
                    console.log('User registered successfully', result);

                    // Navigate back to home.
                    this.router.navigate(['/login']);
                },
                error: (error) => {
                    console.error('There was an error during the registration process', error);
                    // Display error to the user
                    alert('Registration failed. Please double check your form or try again later.');
                }
            });

        }
        registerForm.reset()
    }


}
