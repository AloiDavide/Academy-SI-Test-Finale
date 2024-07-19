import {Component, Input} from '@angular/core';


@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent {
    @Input()
    title: string = "Titolo corso";
    @Input()
    shortDescription: string = "Placeholder descrizione breve corso";
    @Input()
    duration:number = 0;
    @Input()
    imageName: string ="course_placeholder.jpg";

}
