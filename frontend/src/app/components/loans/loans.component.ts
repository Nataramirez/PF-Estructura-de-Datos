import { Component, ViewChild } from '@angular/core';
import { GlobalStateService } from '../../services/global-state.service';
import { Loan } from '../../models/loan.model';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { StateBook } from '../../enums/state-book.enum';
import { CategoryBook } from '../../enums/category-book.enum';
import { ModalComponent } from '../modal/modal.component';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-loans',
  imports: [CommonModule, ModalComponent],
  templateUrl: './loans.component.html',
  styleUrl: './loans.component.css'
})
export class LoansComponent {
  @ViewChild('modalRate') modalRate!: ModalComponent;
  @ViewChild('modalDeleteLoan') modalDeleteLoan!: ModalComponent;
  public loans: Loan[] = [];
  public user: User | null = null;
  public bookRate: Book | null = null;
  public loanDelete: Loan | null = null;
  public stateBookEnum = StateBook;

  constructor(
    private globalState: GlobalStateService
  ) { }

  ngOnInit() {
    this.globalState.loanBooks$.subscribe(loans => {
      this.loans = loans;
    });

    this.globalState.isUserLoggedIn$.subscribe(user => {
      this.user = user;
    });
  }

  public rateBook(book: Book) {
    // acá se debe consumir el servicio del back para calificar el libro
    this.bookRate = book;
    this.modalRate.open();
  }

  public returnBook() {
    // acá se debe consumir el servicio del back para devolver el libro
    // se debe recibir actualizada la lista de prestamos del usuario
    try {
      const loans = [{
        id: '1',
        book: {
          "name": "Algorithms to Live By",
          "author": "Brian Christian",
          "year": "2016",
          "qualification": 2,
          "category": CategoryBook.SCIENCE_FICTION,
          "state": StateBook.AVAILABLE,
          "id": "18"
        },
        dateLoan: '2023-10-01',
        dateReturn: '2023-10-15'
      },
      {
        id: '2',
        book: {
          "name": "Why Nations Fail",
          "author": "Daron Acemoglu",
          "year": "2012",
          "qualification": 1,
          "category": CategoryBook.HISTORY,
          "state": StateBook.AVAILABLE,
          "id": "2"
        },
        dateLoan: '2023-10-01',
        dateReturn: '2023-10-15'
      },
      {
        id: '3',
        book: {
          "name": "The Knowledge",
          "author": "Lewis Dartnell",
          "year": "2014",
          "qualification": 2,
          "category": CategoryBook.SCIENCE_FICTION,
          "state": StateBook.LOANED,
          "id": "4"
        },
        dateLoan: '2023-10-01',
        dateReturn: null
      }]
      this.globalState.setLoanBooks(loans);
      alert('El libro ha sido devuelto correctamente');
    } catch (error) {
      console.error('Error al devolver el libro:', error);
      alert('Error al devolver el libro');
    }
  }

  sendRating(rate: number) {
    console.log('Rating received:', rate);
    console.log('Book to rate:', this.bookRate);
    try {
      // Acá se debe consumir el servicio del back para calificar el libro
      alert('El libro ha sido calificado correctamente');
      this.modalRate.close();
    } catch (error) {
      console.error('Error al calificar el libro:', error);
      alert('Error al calificar el libro');
    }
  }

  deleteLoan(loan: Loan) {
    console.log('Loan to delete:', loan);
    this.loanDelete = loan;
    this.modalDeleteLoan.open();
  }

  confirmDeleteLoan() {
    try {
      // Acá se debe consumir el servicio del back para eliminar el prestamo
      // debe retornar la lista de prestamos actualizada del usuario
      const loans = [{
        id: '1',
        book: {
          "name": "Algorithms to Live By",
          "author": "Brian Christian",
          "year": "2016",
          "qualification": 2,
          "category": CategoryBook.SCIENCE_FICTION,
          "state": StateBook.AVAILABLE,
          "id": "18"
        },
        dateLoan: '2023-10-01',
        dateReturn: '2023-10-15'
      },
      {
        id: '2',
        book: {
          "name": "Why Nations Fail",
          "author": "Daron Acemoglu",
          "year": "2012",
          "qualification": 1,
          "category": CategoryBook.HISTORY,
          "state": StateBook.AVAILABLE,
          "id": "2"
        },
        dateLoan: '2023-10-01',
        dateReturn: '2023-10-15'
      }
    ]
      this.globalState.setLoanBooks(loans);
      alert('El prestamo ha sido eliminado correctamente');
      this.modalDeleteLoan.close();
    } catch (error) {
      console.error('Error al eliminar el prestamo:', error);
      alert('Error al eliminar el prestamo');
    }
  }
}
