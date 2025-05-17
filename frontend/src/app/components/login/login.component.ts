import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../services/global-state.service';
import { Router } from '@angular/router';
import { routesCollection } from '../../app.routes';

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
    // Handle the login logic here
    console.log('Username:', this.username);
    console.log('Password:', this.password);

    const responseCreateUser = true; // acá debo consumir el servicio del back que retorne el usuario encontrado
    if (responseCreateUser) {
      this.router.navigate([routesCollection.MAIN_USER]);
      this.globalState.setUserLoggedIn(true);
    } else {
      alert('Error al crear el usuario');
    }
  }

}
