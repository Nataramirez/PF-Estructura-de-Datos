import { Component, ViewChild } from '@angular/core';
import { ModalComponent } from "../modal/modal.component";
import { RecordComponent } from "../record-user/record.component";
import { CommonModule } from '@angular/common';
import { GlobalStateService } from '../../services/global-state.service';
import { LibraryServicesService } from '../../services/library-services.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-admin-panel-users',
  imports: [ModalComponent, RecordComponent, CommonModule],
  templateUrl: './admin-panel-users.component.html',
  styleUrl: './admin-panel-users.component.css'
})
export class AdminPanelUsersComponent {
deleteUser(arg0: String) {
throw new Error('Method not implemented.');
}
  @ViewChild('modalRecordUser') modalRecordUser!: ModalComponent;
  public users: User[] = []

  constructor(
    private globalState: GlobalStateService,
    private libraryServicesService: LibraryServicesService,
  ) { 
    this.globalState.users$.subscribe((users) => {
      this.users = users;
    });
  }

  openModalLogin() {
    this.modalRecordUser.open();
  }
}
