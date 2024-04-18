import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { MoodleEvent } from 'src/app/models/moodleEvent';
import { EventService } from 'src/app/services/event.service';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {
  authenticatedUser: User | null;
  events: Set<MoodleEvent> = new Set<MoodleEvent>();
  constructor(
    private courseService: CourseService,
    private router: Router,
    private route: ActivatedRoute,
    private eventService: EventService,
    private userService: UserService
  ) { }
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

  deleteEvent(id:number):void{
    this.eventService.deleteEvent(id).subscribe({
      next: () => {
        this.events.forEach(event => {
          if(event.id == id){
            this.events.delete(event);
            return;
          }
        })
      }
    });
  }
}