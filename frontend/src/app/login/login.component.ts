import {Component, EventEmitter, Output} from '@angular/core';
import {LoginRequest} from "../../model/loginRequest";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";
import {UserService} from "../service/user/user.service";
import {Router} from "@angular/router";
import {LocalStorageService} from "../service/local-storage/local.storage.service";

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule, NgIf],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
    providers: [LocalStorageService],
})
export class LoginComponent {
    loginRequest: LoginRequest = new LoginRequest("", "");
    jwtToken: string = "";

    @Output()
    userAccessEvent: EventEmitter<string> = new EventEmitter<string>();

    constructor(private userService: UserService, public router: Router) {

    }

    onSubmit(loginForm: NgForm) {
        console.log(loginForm);
        if (loginForm.valid) {

            //Rest call to the backend to register a user
            this.userService.login(this.loginRequest).subscribe({
                next: (result) => {
                    // On successful call

                    //extract the token and memorize it in the local storage
                    localStorage.setItem('token', result.token);

                    // Emit the event so that app-component can access the data of the user who just logged in.

                    this.userAccessEvent.emit(this.loginRequest.email);

                    // Navigate back to home.
                    this.router.navigate(['/']);

                    loginForm.reset()
                },
                error: (error) => {
                    console.error('There was an error during the login process', error);
                    alert('Login failed. Please double check your form or try again later.');
                }
            });

        }

    }
}
