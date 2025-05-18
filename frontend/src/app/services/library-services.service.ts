import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HelloResponse } from '../records/hello.model';

@Injectable({
  providedIn: 'root'
})

export class LibraryServicesService {
  private portUrl: string = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) { }

  public getHello(): void {
    this.http.get<HelloResponse>(`${this.portUrl}hello`).subscribe({
      next: (data: HelloResponse) => {
        console.log('API is up and running', data);
      },
      error: (error) => {
        console.error('Error connecting to API', error);
      }
    });
  }
}
