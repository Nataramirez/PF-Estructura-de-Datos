import { Component } from '@angular/core';
import { LoginUserComponent } from "../login-user/login-user.component";
import { SearchBooksComponent } from "../search-books/search-books.component";
import { BooksComponent } from "../../books/books.component";

@Component({
  selector: 'app-main-navigation',
  imports: [LoginUserComponent, SearchBooksComponent, BooksComponent],
  templateUrl: './main-navigation.component.html',
  styleUrl: './main-navigation.component.css'
})
export class MainNavigationComponent {

}
