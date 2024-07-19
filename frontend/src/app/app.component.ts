import {Component} from '@angular/core';
import {Router, NavigationEnd, RouterOutlet} from '@angular/router';
import {NavbarComponent} from "./navbar/navbar.component";
import {FooterComponent} from "./footer/footer.component";
import {UserService} from "./service/user/user.service";
import {UserDto} from "../model/userDto";
import {NgIf} from "@angular/common";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {EnrollComponent} from "./enroll/enroll.component";
import {EnrollRequest} from "../model/enrollRequest";
import {LocalStorageService} from "./service/local-storage/local.storage.service";

@Component({
      selector: 'app-root',
      standalone: true,
      imports: [
          RouterOutlet,
          NavbarComponent,
          FooterComponent,
          HomeComponent,
          LoginComponent,
          RegisterComponent,
          EnrollComponent,
          NgIf
      ],
      templateUrl: './app.component.html',
      styleUrl: './app.component.css',
      providers:[UserService, LocalStorageService]
})
export class AppComponent {
    isHomePage:boolean = false;
    isLoginPage:boolean = false;
    isRegisterPage:boolean = false;
    isEnrollPage:boolean = false;
    constructor(public router: Router, private userService:UserService, public localStorageService:LocalStorageService) {
        //somewhat superfluous handling of routing, the app isn't complex enough for this yet
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.isHomePage = this.router.url === '/';
                this.isLoginPage = this.router.url === '/login';
                this.isRegisterPage = this.router.url === '/register';
                this.isEnrollPage = this.router.url === '/enroll';
            }
    });
}




    onUserAccessEvent(email: string) {
        //after a login, fetch the user by email and store their data
        this.userService.getUserByMail(email).subscribe({
            next: (result)=>{
                this.localStorageService.store("loggedUser", JSON.stringify(result));
                location.reload();
            },
            error:(error) =>{
                console.error('There was an error during the registration process', error);
                    alert('Login failed. We couldn\'t find your account.');
                    this.onSignOut();
            }

        });

    }


    onEnrollEvent($event: EnrollRequest) {

    }


    onSignOut() {
        this.localStorageService.clear();
    }
}
