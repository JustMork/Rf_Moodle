import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Degree } from '../models/degree';
import { httpOptions } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DegreeService {

  private apiUrl = '/api/degree';

  constructor(private http: HttpClient) {}

  getAllDegrees(): Observable<Set<Degree>>{
    return this.http.get<Set<Degree>>(`${this.apiUrl}`,{headers: httpOptions.headers});
  }
}
