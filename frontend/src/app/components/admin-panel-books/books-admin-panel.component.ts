import { Component, ViewChild } from '@angular/core';
import { GlobalStateService } from '../../services/global-state.service';
import { Book } from '../../models/book.model';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../modal/modal.component';
import { FormsModule } from '@angular/forms';
import { CategoryBook } from '../../enums/category-book.enum';
import { StateBook } from '../../enums/state-book.enum';
import { LibraryServicesService } from '../../services/library-services.service';

@Component({
  selector: 'app-books-admin-panel',
  imports: [CommonModule, ModalComponent, FormsModule],
  templateUrl: './books-admin-panel.component.html',
  styleUrl: './books-admin-panel.component.css'
})
export class BooksAdminPanelComponent {
  @ViewChild('modalRecordBook') modalRecordBook!: ModalComponent;
  public categories = CategoryBook;
  public books: Book[] = []
  public title: string = '';
  public author: string = '';
  public year: number | undefined;
  public category = '';
  public stateBookEnum = StateBook;

  constructor(
    private globalState: GlobalStateService,
    private libraryServicesService: LibraryServicesService,
  ) {
    this.globalState.books$.subscribe((books) => {
      this.books = books;
    });
  }

  public async onSubmit() {
    this.modalRecordBook.close();
    const requestCreateBook = {
      name: this.title,
      author: this.author,
      category: this.category,
      year: this.year ? this.year : 0,
    }
    const response = await this.libraryServicesService.addBook(requestCreateBook);
    this.globalState.setBooks(response);
    alert('Libro creado correctamente');
    this.title = '';
    this.author = '';
    this.year = undefined;
    this.category = '';
  }

  public async deleteBook(bookId: string) {
    const responseDeleteBook = await this.libraryServicesService.deleteBook(bookId);
    this.globalState.setBooks(responseDeleteBook);
    alert('Libro eliminado correctamente');
  }

  mapCategory(category: CategoryBook) {
    switch (category) {
      case CategoryBook.FANTASY:
        return 'Fantasía';
      case CategoryBook.HISTORY:
        return 'Historia';
      case CategoryBook.ART:
        return 'Arte';
      case CategoryBook.SCIENCE_FICTION:
        return 'Ciencia Ficción';
      case CategoryBook.MYSTERY:
        return 'Misterio';
      case CategoryBook.ROMANCE:
        return 'Romance';
      case CategoryBook.COOKING:
        return 'Cocina';
      default:
        return 'Desconocido';
    }
  }
}