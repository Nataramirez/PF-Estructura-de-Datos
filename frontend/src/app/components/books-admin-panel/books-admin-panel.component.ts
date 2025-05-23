import { Component, ViewChild } from '@angular/core';
import { GlobalStateService } from '../../services/global-state.service';
import { Book } from '../../models/book.model';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../modal/modal.component';
import { FormsModule } from '@angular/forms';
import { CategoryBook } from '../../enums/category-book.enum';
import { StateBook } from '../../enums/state-book.enum';

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
  public title: any;
  public author: any;
  public year: any;
  public category = '';
  public stateBookEnum = StateBook;

  constructor(
    private globalState: GlobalStateService,
  ) {
    this.globalState.books$.subscribe((books) => {
      this.books = books;
    });
  }

  onSubmit() {
    console.log('ACA VAMOS A CREAR EL LIBRO');
    console.log(this.title);
    console.log(this.author);
    console.log(this.year);
    console.log(this.category);
    this.modalRecordBook.close();
    // debo llamar al servicio de crear libro, 
    // debe retornar la nueva lista de libros o llamar al servicio de obtener libros
    // debo actualizar el estado global de los libros
  }

}
