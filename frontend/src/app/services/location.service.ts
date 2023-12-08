import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, Subject, tap} from 'rxjs';
import { Location } from '../models/location.model';

const baseUrl = 'http://localhost:9090/api/location';
@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private refreshSubject: Subject<void> = new Subject<void>();
  triggerRefresh$: Observable<void> = this.refreshSubject.asObservable();

  constructor(private http: HttpClient) { }

  getAll(): Observable<Location[]> {
    return this.http.get<Location[]>(baseUrl+"/get-locations");
  }

  get(id: any): Observable<Location> {
    return this.http.get<Location>(`${baseUrl}/get-location/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl+"/add-location", data)
      .pipe(
      tap(() => this.refreshSubject.next())
    );
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/update-location/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/delete-location/${id}`)
      .pipe(
        tap(() => this.refreshSubject.next())
      );
  }
}
