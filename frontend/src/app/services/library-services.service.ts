import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HelloResponse } from '../records/hello.model';
import { AddBookRequest, AddUserRequest } from './request.model';
import { lastValueFrom, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class LibraryServicesService {
  private portUrl: string = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) { }

  public async getHello(): Promise<HelloResponse> {
    return await lastValueFrom(this.http.get<HelloResponse>(`${this.portUrl}hello`));
  }

  public async addBook(book: AddBookRequest): Promise<any> {
    return await lastValueFrom(this.http.post<any>(`${this.portUrl}book/create`, book));
  }

  public async deleteBook(bookId: string): Promise<any> {
    return await lastValueFrom(this.http.delete<any>(`${this.portUrl}book/delete/${bookId}`));
  }

  public async getAllBooks(): Promise<any> {
    return await lastValueFrom(this.http.get<any>(`${this.portUrl}book/get-all`));
  }

  public async getBookByCategory(category: string): Promise<any> {
    return await lastValueFrom(this.http.get<any>(`${this.portUrl}book/search/category?`, { params: { category } }));
  }

  public async getBookByNameOrAuthor(nameOrAuthor: string): Promise<any> {
    return await lastValueFrom(this.http.get<any>(`${this.portUrl}book/search/name-or-author?`, { params: { param: nameOrAuthor } }));
  }

  public async addUser(user: AddUserRequest): Promise<any> {
    return await lastValueFrom(this.http.post<any>(`${this.portUrl}user/create`, user));
  }
}
