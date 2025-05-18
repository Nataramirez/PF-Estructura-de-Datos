import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Book } from '../models/book.model';

@Injectable({
  providedIn: 'root'
})
export class GlobalStateService {
  private userLoggedIn = new BehaviorSubject<boolean>(false);
  isUserLoggedIn$ = this.userLoggedIn.asObservable();

  private books = new BehaviorSubject<Book[]>([]);
  books$ = this.books.asObservable();

  setUserLoggedIn(state: boolean) {
    this.userLoggedIn.next(state);
  }

  setBooks(books: Book[]) {
    this.books.next(books);
  }
}
