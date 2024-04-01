import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError } from 'rxjs';
import { Course } from 'src/app/models/course';
import { User } from 'src/app/models/user';
import { CourseService } from 'src/app/services/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {

  course: Course = new Course();
  authenticatedUser: User | null;

  constructor(
    private courseService: CourseService,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
  ){}
  ngOnInit(): void {
    let courseIdParam = this.route.snapshot.params['courseId']

    this.userService.authenticatedUser$.subscribe(user => {
      this.authenticatedUser = user;
    });

    if (!isNaN(Number(courseIdParam))) {
      courseIdParam = Number(courseIdParam);
    } else {
      this.router.navigate(['/']);
      return;
    }

    this.courseService.getCourseById(courseIdParam).subscribe((course: Course)=>{
      this.course =  course;
      console.log(course.users);
    });
  }

  enroll(): void {
    this.courseService.enrollCourse(this.course.id).subscribe({
      next: () => {
        this.router.navigate(['/']);

      },
      error: () => {
        alert("Nem lehet felvenni a tárgyat a szakoddal");
        this.router.navigate(['/']);
      }
    });

  }

  unenroll(): void{
    this.courseService.unenrollCourse(this.course.id).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
    });
  }

}






