import { Injectable } from '@angular/core';
import {Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Treasure} from "../models/treasure.model";

const baseUrl = 'http://localhost:9090/api/treasure';
@Injectable({
  providedIn: 'root'
})
export class TreasureService {
  private refreshSubject: Subject<void> = new Subject<void>();
  triggerRefresh$: Observable<void> = this.refreshSubject.asObservable();

  constructor(private http: HttpClient) { }

  getAll(): Observable<Treasure[]> {
    return this.http.get<Treasure[]>(baseUrl+"/get-treasures");
  }

  get(id: any): Observable<Treasure> {
    return this.http.get<Treasure>(`${baseUrl}/get-treasure/${id}`);
  }

  create(data: any, locationId: any): Observable<any> {
    return this.http.post(`${baseUrl}/add-treasure/${locationId}`, data)
      .pipe(
        tap(() => this.refreshSubject.next())
      );
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/update-treasure/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/delete-treasure/${id}`)
      .pipe(
        tap(() => this.refreshSubject.next())
      );
  }

  markAsFound(id: any, unlockId: any): Observable<any> {
    return this.http
      .post(`${baseUrl}/mark-as-found/${id}?unlockId=${unlockId}`, null, {responseType: 'text' })
      .pipe(
        tap(() => this.refreshSubject.next())
      );
  }
}
