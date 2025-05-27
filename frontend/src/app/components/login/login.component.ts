import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../services/global-state.service';
import { Router } from '@angular/router';
import { routesCollection } from '../../app.routes';
import { TypeUser } from '../../enums/type-user.enum';
import { User } from '../../models/user.model';
import { CategoryBook } from '../../enums/category-book.enum';
import { StateBook } from '../../enums/state-book.enum';
import { LibraryServicesService } from '../../services/library-services.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public username: string;
  public password: string;

  constructor(
    private globalState: GlobalStateService,
    private router: Router,
    private libraryService: LibraryServicesService
  ) {
    this.username = '';
    this.password = '';
  }

  public async onSubmit() {
    try {
      const user = await this.libraryService.authUser({
        user: this.username,
        password: this.password
      });
      this.globalState.setUserLoggedIn(user);
      this.globalState.setLoanBooks(user.loans);

      if (user.role === TypeUser.USER) {
        this.router.navigate([routesCollection.MAIN_USER]);
      }

      if (user.role === TypeUser.ADMIN) {
        this.router.navigate([routesCollection.MAIN_ADMIN]);
      }
    } catch (error) {
      alert('Usuario o contraseña incorrectos. Intenta nuevamente');
      return;
    }
  }

}
