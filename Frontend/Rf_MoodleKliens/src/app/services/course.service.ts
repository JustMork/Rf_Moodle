import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/course';
import { httpOptions } from './auth.service';
import { User } from '../models/user';
import { MoodleEvent } from '../models/moodleEvent';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = '/api/course';

  constructor(private http: HttpClient) {}

  enrollCourse(id: number): Observable<Course>{
    return this.http.post<Course>(`${this.apiUrl}/${id}`,{},{headers: httpOptions.headers});
  }

  unenrollCourse(id: number): Observable<Course>{
    return this.http.put<Course>(`${this.apiUrl}/${id}`,{},{headers: httpOptions.headers});
  }  

  getAllCourses(): Observable<Set<Course>>{
    return this.http.get<Set<Course>>(`${this.apiUrl}`,{headers: httpOptions.headers});
  }

  getEnrolledUsers(): Observable<Set<User>>{
    return this.http.get<Set<User>>(`${this.apiUrl}`,{headers: httpOptions.headers});
  }

  getOwnCourses(): Observable<Set<Course>>{
    return this.http.get<Set<Course>>(`${this.apiUrl}/own`,{headers: httpOptions.headers});
  }

  getEvents(id: number): Observable<Set<MoodleEvent>>{
    return this.http.get<Set<MoodleEvent>>(`${this.apiUrl}/${id}/events`,{headers: httpOptions.headers})
  }

  getCourseById(id: number): Observable<Course>{
    return this.http.get<Course>(`${this.apiUrl}/${id}`,{headers: httpOptions.headers});
  }

}
