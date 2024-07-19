import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgIf} from "@angular/common";
import {RouterOutlet, RouterLink, RouterLinkActive} from "@angular/router";
import {UserDto} from "../../model/userDto";
import {LocalStorageService} from "../service/local-storage/local.storage.service";

@Component({
    selector: 'app-navbar',
    standalone: true,
    imports: [
        NgIf,
        RouterOutlet,
        RouterLink,
        RouterLinkActive
    ],
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.css',
    providers: [LocalStorageService]
})
export class NavbarComponent {
    logged:boolean = false;
    loggedUser: UserDto | null = null;

    @Output()
    userSignOutEvent: EventEmitter<any> = new EventEmitter<any>();
    constructor(public localStorageService:LocalStorageService) {
        if (localStorageService.get("loggedUser") != null){
            console.log(localStorageService.get("loggedUser"));
            this.loggedUser = JSON.parse(<string>localStorageService.get("loggedUser"));
            this.logged = true;
        }
        console.log('Currently logged in navbar:');
        console.log(this.loggedUser);
        console.log(this.logged);
    }
    onSignOut() {
        this.userSignOutEvent.emit();
    }
}
