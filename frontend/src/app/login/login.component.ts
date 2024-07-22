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


    constructor(private userService: UserService,
                public router: Router,
                public localStorageService:LocalStorageService) {

    }

    onSubmit(loginForm: NgForm) {
        console.log(loginForm);
        if (loginForm.valid) {

            //Rest call to the backend to register a user
            this.userService.login(this.loginRequest).subscribe({
                next: (result) => {
                    // On successful call

                    //extract the token and memorize it in the local storage
                    this.localStorageService.store('token', result.token);

                    // Emit the event so that app-component can access the data of the user who just logged in.
                    this.userService.getUserByMail(this.loginRequest.email).subscribe({
                        next: (result)=>{
                            this.localStorageService.store("loggedUser", JSON.stringify(result));
                            this.router.navigate(["/forecast"])

                        },
                        error:(error) =>{
                            console.error('There was an error during the registration process', error);
                                alert('Login failed. We couldn\'t find your account.');
                                this.localStorageService.clear();
                        }

                    });




                },
                error: (error) => {
                    console.error('There was an error during the login process', error);
                    alert('Login failed. Please double check your form or try again later.');
                }
            });

        }

    }
}
