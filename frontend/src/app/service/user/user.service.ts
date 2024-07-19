import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {UserDto} from "../../../model/userDto";
import {catchError, Observable, retry, throwError} from "rxjs";
import {RegisterRequest} from "../../../model/registerRequest";
import {LoginRequest} from "../../../model/loginRequest";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    RETRY_COUNT: number = 3;

    //Dependency injection del client per le chiamate rest
    constructor(private http: HttpClient) {
    }

    getAll(): Observable<UserDto[]> {
        return this.http.get<UserDto[]>('http://localhost:8080/api/user/get/all').pipe(
            retry(this.RETRY_COUNT)
        );
    }

    getUserByMail(email: string): Observable<UserDto> {
        return this.http.get<UserDto>('http://localhost:8080/api/user/get/' + email).pipe(
            retry(this.RETRY_COUNT)
        );
    }


    register(registerRequest: RegisterRequest) {
        //Here the request dto is passed as is.
        return this.http.post<any>('http://localhost:8080/api/user/reg', registerRequest).pipe(
            retry(this.RETRY_COUNT)
        );
    }

    login(loginRequest: LoginRequest){
        //This time the request dto is passed as a json.
        var headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post<any>('http://localhost:8080/api/user/login', JSON.stringify(loginRequest), {headers: headers}).pipe(
            retry(this.RETRY_COUNT)
        )

    }



    //DEFAULT ERROR HANDLING - CURRENTLY NOT USED
    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error);
        }
        else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong.
          console.error(
            `Backend returned code ${error.status}, body was: `, error.error);
        }
         // Return an observable with a user-facing error message
        return throwError(() => new Error('Something bad happened; please try again later.'));
    }



}


