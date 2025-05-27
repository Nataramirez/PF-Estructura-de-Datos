import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HelloResponse } from '../records/hello.model';
import { AddBookRequest, AddUserRequest, AuthUserRequest, CancelLoanRequest, LoanBookRequest, RateBookRequest, ReturnBookRequest, UpdateUserRequest } from './request.model';
import { lastValueFrom, Observable } from 'rxjs';
import { Loan } from '../models/loan.model';
import { User } from '../models/user.model';

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

  public async authUser(authUser: AuthUserRequest): Promise<any> {
    return await lastValueFrom(this.http.post<any>(`${this.portUrl}user/auth`, authUser));
  }

  public async getAllUsers(): Promise<any> {
    return await lastValueFrom(this.http.get<any>(`${this.portUrl}user/get-all`));
  }

  public async updateUser(updateUserRequest: UpdateUserRequest): Promise<User[]>{
    return await lastValueFrom(this.http.put<User[]>(`${this.portUrl}user/update`, updateUserRequest))
  }

  public async deleteUser(user: string): Promise<any>{
    return await lastValueFrom(this.http.delete<any>(`${this.portUrl}user/delete/${user}`))
  }

  public async loanBook(loanBookRequest: LoanBookRequest): Promise<any> {
    const { idBook, userForApply } = loanBookRequest;
    return await lastValueFrom(
      this.http.post<any>(
        `${this.portUrl}loan/apply`,
        {},
        { params: { idBook, userForApply } }
      )
    );
  }

  public async returnBook(returnBookRequest: ReturnBookRequest): Promise<Loan[]> {
    const { userString, idLoan } = returnBookRequest;
    return await lastValueFrom(
      this.http.post<Loan[]>(
        `${this.portUrl}loan/return`,
        {},
        { params: { userString, idLoan } }
      )
    );
  }

  public async rateBook(rateBookRequest: RateBookRequest): Promise<any> {
    const { qualification, idLoan } = rateBookRequest;
    return await lastValueFrom(this.http.post<any>(`${this.portUrl}loan/qualify-loan/${idLoan}`, {}, { params: { qualification } }));
  }

  public async cancelLoan(cancelLoanRequest: CancelLoanRequest): Promise<Loan[]> {
    const { userString, idLoan } = cancelLoanRequest;
    return await lastValueFrom(
      this.http.post<Loan[]>(
        `${this.portUrl}loan/cancel?`,
        {},
        { params: { userString, idLoan } }
      )
    );
  }

  public async getLoanByUser(user: string): Promise<Loan[]>{
    return await lastValueFrom(this.http.get<Loan[]>(`${this.portUrl}loan/get-loans-user?`, { params: { userString: user }}))
  }
}
