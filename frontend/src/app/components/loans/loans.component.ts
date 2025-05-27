import { Component, ViewChild } from '@angular/core';
import { GlobalStateService } from '../../services/global-state.service';
import { Loan } from '../../models/loan.model';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { StateBook } from '../../enums/state-book.enum';
import { CategoryBook } from '../../enums/category-book.enum';
import { ModalComponent } from '../modal/modal.component';
import { Book } from '../../models/book.model';
import { LibraryServicesService } from '../../services/library-services.service';
import { RateBookRequest, ReturnBookRequest } from '../../services/request.model';
import { LoanStates } from '../../models/loan.model';

@Component({
  selector: 'app-loans',
  imports: [CommonModule, ModalComponent],
  templateUrl: './loans.component.html',
  styleUrl: './loans.component.css'
})
export class LoansComponent {
  @ViewChild('modalRate') modalRate!: ModalComponent;
  @ViewChild('modalDeleteLoan') modalDeleteLoan!: ModalComponent;
  public loanRate: Loan | null = null;
  public loans: Loan[] = [];
  public user: User | null = null;
  public bookRate: Book | null = null;
  public loanDelete: Loan | null = null;
  public stateBookEnum = StateBook;
  public loanStates = LoanStates;
  loanBtn: HTMLButtonElement | null = null;

  constructor(
    private globalState: GlobalStateService,
    private libraryServicesService: LibraryServicesService
  ) { }

  ngOnInit() {
    this.globalState.loanBooks$.subscribe(loans => {
      this.loans = loans;
      console.log('Lista de prestamos actualizada:', this.loans);

    });

    this.globalState.isUserLoggedIn$.subscribe(user => {
      this.user = user;
    });
  }

  public rateBook(loan: Loan | undefined) {
    if (loan) {
      this.bookRate = loan.book;
      this.loanRate = loan;
      this.modalRate.open();
    }
  }

  public async returnBook(loan: Loan) {
    if (this.user) {
      const requestReturn: ReturnBookRequest = {
        userString: this.user.user,
        idLoan: loan.id
      }
      const responseReturn: Loan[] = await this.libraryServicesService.returnBook(requestReturn)
      this.globalState.setLoanBooks(responseReturn);
      alert('El libro ha sido devuelto correctamente');
    }
  }

  public async sendRating(rate: number) {
    if (this.bookRate && this.loanRate) {
      const requestRate: RateBookRequest = {
        qualification: rate,
        idLoan: this.loanRate.id
      }
      const responseRate = await this.libraryServicesService.rateBook(requestRate)
      alert('El libro ha sido calificado correctamente');
      this.globalState.setBooks(responseRate);
      this.modalRate.close();
    }
  }

  deleteLoan(loan: Loan) {
    this.loanDelete = loan;
    this.modalDeleteLoan.open();
  }

  public async confirmDeleteLoan() {
    if (this.user && this.loanDelete) {
      const requestCancelLoan = {
        userString: this.user.user,
        idLoan: this.loanDelete.id
      }
      const responseCancelLoan: Loan[] = await this.libraryServicesService.cancelLoan(requestCancelLoan);
      this.globalState.setLoanBooks(responseCancelLoan); 
      alert('El prestamo ha sido eliminado correctamente');
      this.modalDeleteLoan.close();
    }
  }
}
