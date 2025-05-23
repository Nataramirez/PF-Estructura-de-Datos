import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../services/global-state.service';
import { Router } from '@angular/router';
import { routesCollection } from '../../app.routes';
import { User } from '../../models/user.model';
import { TypeUser } from '../../enums/type-user.enum';

@Component({
  selector: 'app-record',
  imports: [FormsModule, CommonModule],
  templateUrl: './record.component.html',
  styleUrl: './record.component.css'
})
export class RecordComponent {
  public username: string = '';
  public password: string = '';
  public name: string = '';
  public updateUser: boolean = false;
  private userId: String = '';

  constructor(
    private globalState: GlobalStateService,
    private router: Router
  ) {
    this.globalState.isUserLoggedIn$.subscribe((user) => {
      if (user) {
        this.username = user.identification;
        this.password = user.password;
        this.name = user.name;
        this.updateUser = true;
        this.userId = user.id;
      }
    });
  }

  onSubmit() {
    // Handle the record logic here
    console.log('Username:', this.username);
    console.log('Password', this.password);
    console.log('Name:', this.name);
    try {
      // si el updateUser es true, debo consumir el servicio para actualizar el usuario
      // y que retorne el usuario actualizado
      // de lo contrario debo consumir el servicio para crear el usuario y que retorne el usuario creado
      const responseCreateUser: User = { 
        id: '1',
        name: this.name,
        password: this.password,
        identification: this.username,
        type: TypeUser.USER,
        loans: [],
        scores: []
      };

      if (responseCreateUser) {
        this.globalState.setUserLoggedIn(responseCreateUser);
        this.router.navigate([routesCollection.MAIN_USER]);
      } else {
        console.error('Error al crear el usuario', responseCreateUser);
        alert('Error al crear el usuario');
      }
    } catch (error) {
      console.error('Error creating user:', error);
      alert('Error al crear el usuario'); 
    }

  }

}
