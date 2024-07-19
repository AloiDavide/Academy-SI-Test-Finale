import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CourseDto} from "../../../model/courseDto";
import {catchError, Observable, retry, throwError} from "rxjs";
import {CategoryDto} from "../../../model/categoryDto";

@Injectable({
    providedIn: 'root'
})
export class CourseService {

    //Dependency injection del client per le chiamate rest
    constructor(private http: HttpClient) {
    }

    getAll(): Observable<CourseDto[]> {
        return this.http.get<CourseDto[]>('http://localhost:8080/api/course/get/all').pipe(
            retry(3),
            catchError(this.handleError)
        );
    }


    getAllCategories(): Observable<CategoryDto[]> {
        return this.http.get<CategoryDto[]>('http://localhost:8080/api/category/get/all').pipe(
            retry(3),
            catchError(this.handleError)
        );
    }

    getByCategory(categoryId: number): Observable<CourseDto[]> {
        return this.http.get<CourseDto[]>('http://localhost:8080/api/course/get/byCategory/'+categoryId).pipe(
            retry(3),
            catchError(this.handleError)
        );
    }

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
         // Return an observable with a course-facing error message
        return throwError(() => new Error('Something bad happened; please try again later.'));
    }
}


