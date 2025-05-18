import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CategoryBook } from '../../../enums/category-book.enum';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search-books',
  imports: [CommonModule, FormsModule],
  templateUrl: './search-books.component.html',
  styleUrl: './search-books.component.css'
})
export class SearchBooksComponent {
  public categories = CategoryBook;
  public searchName: string = '';
  public searhCategory = '';

  searchBook(searchInput: string): void {
    console.log('Buscando libros en la categoria:', searchInput);
    if (searchInput !== '') {
      // aca consumir el servicio de busqueda de libros
      if (Object.values(CategoryBook).includes(searchInput as CategoryBook)) {
        console.log('Buscando libros por categoria:', searchInput);
      } else {
        console.log('Buscando libros por nombre o autor:', searchInput);
      }
    }
    this.searchName = '';
    this.searhCategory = '';
  }

  allbooks() {
    alert('aca debemos llamar al back y actualizar la lista de libros');
  }
}
