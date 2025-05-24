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
    private router: Router
  ) {
    this.username = '';
    this.password = '';
  }

  public onSubmit() {
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    try {
      const responseSearchUser: User = { // acá debo consumir el servicio del back que retorne el usuario encontrado
        id: '1',
        name: 'natalia',
        password: '123456',
        identification: '123456789',
        type: TypeUser.ADMIN,
        loans: [{
          id: '1',
          book: {
            "title": "Algorithms to Live By",
            "author": "Brian Christian",
            "year": "2016",
            "qualification": 2,
            "category": CategoryBook.SCIENCE_FICTION,
            "state": StateBook.AVAILABLE
          },
          dateLoan: '2023-10-01',
          dateReturn: '2023-10-15'
        },
        {
          id: '2',
          book: {
            "title": "Why Nations Fail",
            "author": "Daron Acemoglu",
            "year": "2012",
            "qualification": 1,
            "category": CategoryBook.HISTORY,
            "state": StateBook.AVAILABLE
          },
          dateLoan: '2023-10-01',
          dateReturn: null
        },
        {
          id: '3',
          book: {
            "title": "The Knowledge",
            "author": "Lewis Dartnell",
            "year": "2014",
            "qualification": 2,
            "category": CategoryBook.SCIENCE_FICTION,
            "state": StateBook.LOANED
          },
          dateLoan: '2023-10-01',
          dateReturn: null
        }],
        scores: [
          {
            id: '1',
            book: {
              "title": "Algorithms to Live By",
              "author": "Brian Christian",
              "year": "2016",
              "qualification": 2,
              "category": CategoryBook.SCIENCE_FICTION,
              "state": StateBook.AVAILABLE
            },
            value: 5
          },
          {
            id: '2',
            book: {
              "title": "Why Nations Fail",
              "author": "Daron Acemoglu",
              "year": "2012",
              "qualification": 1,
              "category": CategoryBook.HISTORY,
              "state": StateBook.AVAILABLE
            },
            value: 4  
          }
        ]
      };
      this.globalState.setUserLoggedIn(responseSearchUser);
      this.globalState.setLoanBooks(responseSearchUser.loans);

      if (responseSearchUser.type === TypeUser.USER) {
        this.router.navigate([routesCollection.MAIN_USER]);
      } else if (responseSearchUser.type === TypeUser.ADMIN) {
        this.router.navigate([routesCollection.MAIN_ADMIN]);
      } else {
        alert('Usuario no fue encontrado. Intenta nuevamente');
        this.globalState.setUserLoggedIn(null);
      }
    } catch (error) {
      console.error('Error al iniciar sesión:', error);
      alert('Error al iniciar sesión. Intenta nuevamente');
    }


  }

}
