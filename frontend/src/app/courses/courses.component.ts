import {Component} from '@angular/core';
import {CourseCardComponent} from "../course-card/course-card.component";
import {CourseDto} from "../../model/courseDto";
import {CommonModule} from "@angular/common";
import {CourseService} from "../service/course/course.service";

@Component({
    selector: 'app-courses',
    standalone: true,
    imports: [CourseCardComponent, CommonModule],
    templateUrl: './courses.component.html',
    styleUrl: './courses.component.css',
    providers: [CourseService]
})
export class CoursesComponent {
    categories: string[] = [];

    currentCategory: string = "frontend";


    courses: CourseDto[] = [];

    coursesByCategory: { [key: string]: CourseDto[] } = {}

    constructor(private courseService: CourseService) {
        this.courseService.getAllCategories().subscribe(result => {
            console.log("Categories: \n"+result);
            this.categories = result.map(category => category.categoryName);

            result.forEach(category => {
                this.courseService.getByCategory(category.id).subscribe(result => {
                    this.coursesByCategory[category.categoryName.toLowerCase()] = result;
                })
            })
        })

        this.courseService.getAll().subscribe(result => {
            console.log(result);
            this.courses = result;
        })
    }

    changeCategory(category: string) {
        this.currentCategory = category;
    }

}
