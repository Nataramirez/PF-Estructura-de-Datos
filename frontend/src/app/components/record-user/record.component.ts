import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../services/global-state.service';
import { Router } from '@angular/router';
import { routesCollection } from '../../app.routes';

@Component({
  selector: 'app-record',
  imports: [FormsModule, CommonModule],
  templateUrl: './record.component.html',
  styleUrl: './record.component.css'
})
export class RecordComponent {
  public username: string;
  public password: string;
  public name: string;

  constructor(
    private globalState: GlobalStateService,
    private router: Router
  ) {
    this.username = '';
    this.password = '';
    this.name = '';
  }

  onSubmit() {
    // Handle the record logic here
    console.log('Username:', this.username);
    console.log('Password', this.password);
    console.log('Name:', this.name);
    const responseCreateUser = true; // acá debo consumir el servicio del back 
    // 
    if(responseCreateUser){
      this.router.navigate([routesCollection.MAIN_USER]);
      this.globalState.setUserLoggedIn(true);
    }else {
      alert('Error al crear el usuario');
    }
  }

}
