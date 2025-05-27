import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CategoryBook } from '../../../enums/category-book.enum';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../../services/global-state.service';
import { TypeUser } from '../../../enums/type-user.enum';
import { Router } from '@angular/router';
import { routesCollection } from '../../../app.routes';
import { LibraryServicesService } from '../../../services/library-services.service';
import { BinaryTreeService } from '../../../services/binary-tree/binary-tree.service';

@Component({
  selector: 'app-search-books',
  imports: [CommonModule, FormsModule],
  templateUrl: './search-books.component.html',
  styleUrl: './search-books.component.css'
})
export class SearchBooksComponent {
  public categories = CategoryBook;
  public searchName: string = '';
  public searchCategory = '';

  constructor(
    private globalState: GlobalStateService,
    private router: Router,
    private libraryServicesService: LibraryServicesService,
  ) {
  }

  public async searchBook(searchInput: string): Promise<void> {
    if (searchInput !== '') {
      if (Object.values(CategoryBook).includes(searchInput as CategoryBook)) {
        const responseSearchCategory = await this.libraryServicesService.getBookByCategory(searchInput);
        this.globalState.setBooks(responseSearchCategory);
        this.getUserState();
      } else {
        const responseSearchNameOrAuthor = await this.libraryServicesService.getBookByNameOrAuthor(searchInput);
        this.globalState.setBooks(responseSearchNameOrAuthor);
        this.getUserState();
      }
      this.searchName = '';
      this.searchCategory = '';
    } else {
      alert('Por favor ingrese un nombre o categoria para buscar');
    }
    
  }

  public async allbooks() {
    const getBooks = await this.libraryServicesService.getAllBooks();
    this.globalState.setBooks(getBooks);
    this.getUserState();
  }

  public getUserState() {
    this.globalState.isUserLoggedIn$.subscribe(user => {
      if (user) {
        if (user.role === TypeUser.USER) {
          this.router.navigate([routesCollection.MAIN_USER], { replaceUrl: true });
        } else if (user.role === TypeUser.ADMIN) {
          this.router.navigate([routesCollection.MAIN_ADMIN]);
        }
      }
    });
  }
}
