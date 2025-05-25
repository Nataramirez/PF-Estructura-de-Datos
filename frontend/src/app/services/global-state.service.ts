import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Book } from '../models/book.model';
import { User } from '../models/user.model';
import { Loan } from '../models/loan.model';

@Injectable({
  providedIn: 'root'
})
export class GlobalStateService {
  /**
   *  * This service is used to manage the global state of the user.
   */
  private userLoggedIn = new BehaviorSubject<User | null>(null);
  isUserLoggedIn$ = this.userLoggedIn.asObservable();

  setUserLoggedIn(user: User | null) {
    this.userLoggedIn.next(user);
  }

  /**
   * This service is used to manage the global state of the books.
   */
  private books = new BehaviorSubject<Book[]>([]);
  books$ = this.books.asObservable();

  setBooks(books: Book[]) {
    this.books.next(books);
  }

  /**
   * This service is used to manage the global state of the loan books.
   */
  private loanBooks = new BehaviorSubject<Loan[]>([]);
  loanBooks$ = this.loanBooks.asObservable();

  setLoanBooks(loanBooks: Loan[]) {
    this.loanBooks.next(loanBooks);
  }

  /**
   * This service is used to manage the global state of the users.
   */
  private users = new BehaviorSubject<User[]>([]);
  users$ = this.users.asObservable();

  setUsers(users: User[]) {
    this.users.next(users);
  }
}

