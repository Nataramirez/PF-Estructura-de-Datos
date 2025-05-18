import { Component, ViewChild } from '@angular/core';
import { ModalComponent } from "../../modal/modal.component";
import { LoginComponent } from "../../login/login.component";
import { RecordComponent } from "../../record-user/record.component";

@Component({
  selector: 'app-login-user',
  imports: [ModalComponent, LoginComponent, RecordComponent],
  templateUrl: './login-user.component.html',
  styleUrl: './login-user.component.css'
})
export class LoginUserComponent {
  @ViewChild('modalLogin') modalLogin!: ModalComponent;
  @ViewChild('modalRecord') modalRecord!: ModalComponent;

  openModalLogin() {
    this.modalLogin.open();
  }

  openModalRecord() {
    this.modalRecord.open();
  }
}
