import { CommonModule } from '@angular/common';
import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit, ViewChild } from '@angular/core';
import { Book } from '../../models/book.model';
import { ModalComponent } from '../modal/modal.component';
import { GlobalStateService } from '../../services/global-state.service';
import { LoginComponent } from "../login/login.component";
import { StateBook } from '../../enums/state-book.enum';

@Component({
  selector: 'app-books',
  imports: [CommonModule, ModalComponent, LoginComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent implements OnInit {
  @ViewChild('modal') modal!: ModalComponent;
  @ViewChild('modalLoan') modalLoan!: ModalComponent;
  private isUserLoggedIn: boolean = false;
  public books: Book[] = [];
  public descriptionLoan: string = '';
  public nameBookLoan: string = '';
  public stateBookLoan: StateBook = StateBook.AVAILABLE;

  constructor(
    private globalState: GlobalStateService
  ) { }

  ngOnInit() {
    this.globalState.isUserLoggedIn$.subscribe(state => {
      console.log('Usuario logueado:', state);
      this.isUserLoggedIn = state;
    });
    this.globalState.books$.subscribe(books => {
      console.log('Libros:', books);
      this.books = books;
    });
  }

  public openModal(book: Book): void {
    if (!this.isUserLoggedIn) {
      this.modal.open();
    } else {
      console.log('book', book);
      this.validateStateBook(book);
    }
  }

  public validateStateBook(book: Book): void {
    const { state } = book;
    this.nameBookLoan = book.title;
    this.stateBookLoan = state;
    if (state === StateBook.AVAILABLE) {
      this.descriptionLoan = 'Vas a solicitar el prestamo del libro: ';
      this.modalLoan.open();
    } else if (state === StateBook.LOANED) {
      this.descriptionLoan = 'El libro no se encuentra disponible, entrarás en la lista de espera. ¿Deseas continuar?';
      this.modalLoan.open();
    }
  }

  public getClassByState(state: StateBook): string {
    switch (state) {
      case StateBook.AVAILABLE:
        return 'state-available';
      case StateBook.LOANED:
        return 'state-loaned';
      default:
        return '';
    }
  }

}
