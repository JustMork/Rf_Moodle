import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit{
  

  courses: Set<Course> = new Set<Course>();

  constructor(
    private courseService: CourseService,
  ){}
  ngOnInit(): void {
    this.courseService.getAllCourses().subscribe((courses: Set<Course>)=>{
      console.log(courses);
      this.courses = new Set([...courses]);
    });
  }



}
