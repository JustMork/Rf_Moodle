import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Degree } from 'src/app/models/degree';
import { CourseService } from 'src/app/services/course.service';
import { DegreeService } from 'src/app/services/degree.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  degrees: Set<Degree> = new Set<Degree>();
  courses: Set<Course> = new Set<Course>();
  showingCourses: Set<Course> = new Set<Course>();

  constructor(
    private courseService: CourseService,
    private degreeService: DegreeService,
  ) { }

  filterCoursesByDegree(degree: Degree): void {
    this.showingCourses = new Set([...this.courses].filter(course => {
      for (let approvedDegree of course.approvedDegrees) {
        if (approvedDegree.id === degree.id) {
          return true;
        }
      }
      return false;
    }));
  }

  resetCourses(){
    this.showingCourses = new Set([...this.courses]);
  }
  
  


  ngOnInit(): void {
    this.courseService.getAllCourses().subscribe((courses: Set<Course>) => {
      console.log(courses);
      this.courses = new Set([...courses]);
      this.courses.forEach(course => {
        course.approvedDegrees = new Set([...course.approvedDegrees]);
      }) 
      this.showingCourses = new Set([...courses]);

    });

    this.degreeService.getAllDegrees().subscribe((degrees: Set<Degree>) => {
      console.log(this.degrees);
      this.degrees = new Set([...degrees]);
    });

  }
}
