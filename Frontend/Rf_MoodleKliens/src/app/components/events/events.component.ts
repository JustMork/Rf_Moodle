import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { MoodleEvent } from 'src/app/models/moodleEvent';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events: Set<MoodleEvent> = new Set<MoodleEvent>();
  constructor(
    private courseService: CourseService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }
  ngOnInit(): void {
    let courseIdParam = this.route.snapshot.params['courseId']

    if (!isNaN(Number(courseIdParam))) {
      courseIdParam = Number(courseIdParam);
    } else {
      this.router.navigate(['/']);
      return;
    }

    this.courseService.getEvents(courseIdParam).subscribe({

      next: (events) => {
        this.events = new Set([...events]);
      },
      error: (err) => {
        if (err.status == 403) {
          this.router.navigate(['/']);
          alert("Nem nézheted meg az eseményeket");
        }
      }
    });
  };
}