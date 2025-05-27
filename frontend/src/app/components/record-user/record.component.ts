import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GlobalStateService } from '../../services/global-state.service';
import { Router } from '@angular/router';
import { routesCollection } from '../../app.routes';
import { User } from '../../models/user.model';
import { TypeUser } from '../../enums/type-user.enum';
import { LibraryServicesService } from '../../services/library-services.service';

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
  public newUser: boolean = false;

  constructor(
    private globalState: GlobalStateService,
    private router: Router,
    private libraryService: LibraryServicesService
  ) {
    this.globalState.isUserLoggedIn$.subscribe((user) => {
      if (user && user.role === TypeUser.ADMIN) {
        this.newUser = true;
      }

      if (user && user.role === TypeUser.USER) {
        this.username = user.user;
        this.password = user.password;
        this.name = user.name;
        this.updateUser = true;
        this.userId = user.id;
      }
    });
  }

  public async onSubmit() {
    if (this.updateUser) {
      const requestUpdateUser = {
        user: this.username,
        name: this.name,
        password: this.password
      }
      const responseUpdateUsers = await this.libraryService.updateUser(requestUpdateUser);
      console.log(responseUpdateUsers);
      const users = await this.libraryService.getAllUsers();
      this.globalState.setUsers(users);
      
    } else {
      const responseCreateUser = await this.libraryService.addUser({
        user: this.username,
        password: this.password,
        name: this.name,
        role: TypeUser.USER
      })
      if (responseCreateUser && !this.newUser) {
        this.globalState.setUserLoggedIn(responseCreateUser);
        this.router.navigate([routesCollection.MAIN_USER], { replaceUrl: true });
      } else if (responseCreateUser && this.newUser) {
        const users = await this.libraryService.getAllUsers();
        this.globalState.setUsers(users);
        this.router.navigate([routesCollection.MAIN_ADMIN, routesCollection.ADMIN_USERS]);
        alert('Usuario creado correctamente');
      }
    }
    this.username = '';
    this.password = '';
    this.name = '';
    this.updateUser = false;
  }
}
