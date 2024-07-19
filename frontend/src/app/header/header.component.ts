import {Component} from '@angular/core';
import {LocalStorageService} from "../service/local-storage/local.storage.service";
import {NgIf} from "@angular/common";

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [
        NgIf
    ],
    templateUrl: './header.component.html',
    styleUrl: './header.component.css',
    providers: [LocalStorageService]
})
export class HeaderComponent {
    isLogged: boolean = false;
    constructor(public localStorageService: LocalStorageService) {
        if (localStorageService.get("loggedUser") != null) {
            this.isLogged = true;
        }
    }
}
