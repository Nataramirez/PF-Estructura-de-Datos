import { Component } from '@angular/core';
import { SearchBooksComponent } from "../search-books/search-books.component";
import { GlobalStateService } from '../../../services/global-state.service';
import { Router, RouterModule } from '@angular/router';
import { routesCollection } from '../../../app.routes';
import { Book } from '../../../models/book.model';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../../modal/modal.component';
import { LibraryServicesService } from '../../../services/library-services.service';

@Component({
  selector: 'app-main-admin',
  imports: [RouterModule, CommonModule, SearchBooksComponent, ModalComponent],
  templateUrl: './main-admin.component.html',
  styleUrl: './main-admin.component.css'
})
export class MainAdminComponent {
  public books: Book[] = [];

  constructor(
    private globalState: GlobalStateService,
    private router: Router,
    private libraryService: LibraryServicesService // Aquí se debería inyectar el servicio de biblioteca
  ) {
    this.globalState.books$.subscribe((books) => {
      this.books = books;
    });
  }

  public async closeSession() {
    this.globalState.setUserLoggedIn(null);
    const getBooks = await this.libraryService.getAllBooks();
    this.globalState.setBooks(getBooks);
    this.router.navigate([routesCollection.HOME]);
  }

  public goToFlows(flow: string) {
    switch (flow) {
      case 'users':
        this.router.navigate([routesCollection.MAIN_ADMIN, routesCollection.ADMIN_USERS], { replaceUrl: true });
        break;
      case 'affinity':
        this.router.navigate([routesCollection.MAIN_ADMIN, routesCollection.ADMIN_AFFINITY], { replaceUrl: true });
        break;
      case 'statistics':
        this.router.navigate([routesCollection.MAIN_ADMIN, routesCollection.ADMIN_STADISTICS], { replaceUrl: true });
        break;
      default:
        this.router.navigate([routesCollection.MAIN_ADMIN, routesCollection.ADMIN_USERS], { replaceUrl: true });
        break;
    }
  }

}
