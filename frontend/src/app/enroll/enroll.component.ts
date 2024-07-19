import {Component, EventEmitter, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CourseService} from "../service/course/course.service";
import {CategoryDto} from "../../model/categoryDto";
import {EnrollRequest} from "../../model/enrollRequest";
import {FormsModule, NgForm} from "@angular/forms";

@Component({
    selector: 'app-enroll',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './enroll.component.html',
    styleUrl: './enroll.component.css'
})
export class EnrollComponent {
    categories: CategoryDto[] = [];
    selectedCategory: string = "";
    courseTitles:{ [key: string]: string[] } = {};
    selectedList: string[] = [];
    selectedCourse:string=""

    @Output() enrollEvent = new EventEmitter<EnrollRequest>();


    constructor(private courseService: CourseService) {
        this.courseService.getAllCategories().subscribe(result => {

            this.categories = result;

            result.forEach(category => {
                this.courseService.getByCategory(category.id).subscribe(result => {
                    this.courseTitles[category.categoryName.toLowerCase()] = result.map(course => course.name);
                })
            })
        })


    }


    onCategoryChange($event: Event, enrollForm: NgForm){
        enrollForm.reset()
        const selectElement:HTMLSelectElement = $event.target as HTMLSelectElement;
        const category:string = selectElement.value;
        console.log(category);
        this.selectedCategory = category;
        this.selectedList = this.courseTitles[category];


    }

    onSubmit(enrollForm: NgForm) {
        //TODO implement the service on the frontend side
        console.log("Enroll submission");
    }
}
