import { Component } from '@angular/core';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-mycourses',
  templateUrl: './mycourses.component.html',
  styleUrls: ['./mycourses.component.scss']
})
export class MycoursesComponent {

  courses: Set<Course> = new Set<Course>();

  constructor(
    private courseService: CourseService,
  ){}
  ngOnInit(): void {
    this.courseService.getOwnCourses().subscribe((courses: Set<Course>)=>{
      console.log(courses);
      this.courses = new Set([...courses]);
    });
  }

}

