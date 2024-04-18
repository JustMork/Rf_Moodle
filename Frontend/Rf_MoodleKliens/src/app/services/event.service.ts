import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { httpOptions } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { MoodleEvent } from '../models/moodleEvent';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = '/api/event';

  constructor(
   private http: HttpClient
  )
   { }

  createEvent(id: number, event: MoodleEvent): Observable<MoodleEvent>{
    return this.http.post<MoodleEvent>(`${this.apiUrl}/${id}`,event,{headers: httpOptions.headers});
  }

  deleteEvent(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`,{headers: httpOptions.headers});
  }

}
