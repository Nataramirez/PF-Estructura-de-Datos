import { Component, ViewChild } from '@angular/core';
import { SearchBooksComponent } from "../search-books/search-books.component";
import { GlobalStateService } from '../../../services/global-state.service';
import { Router, RouterModule } from '@angular/router';
import { routesCollection } from '../../../app.routes';
import { ModalComponent } from "../../modal/modal.component";
import { RecordComponent } from "../../record-user/record.component";

@Component({
  selector: 'app-main-user',
  imports: [SearchBooksComponent, RouterModule, ModalComponent, RecordComponent],
  templateUrl: './main-user.component.html',
  styleUrl: './main-user.component.css'
})

export class MainUserComponent {
  @ViewChild('modalUpdateProfile') modalUpdateProfile!: ModalComponent;

  constructor(
    private globalState: GlobalStateService,
    private router: Router
  ) { }

  public closeSession() {
    this.globalState.setUserLoggedIn(false);
    this.router.navigate([routesCollection.HOME]);
  }

  public goToFlows(flow: string) {
    switch (flow) {
      case 'loans':
        this.router.navigate([routesCollection.MAIN_USER, routesCollection.USER_LOANS], { replaceUrl: true });
        break;
      case 'recommendations':
        this.router.navigate([routesCollection.MAIN_USER, routesCollection.USER_RECOMMENDATIONS], { replaceUrl: true });
        break;
      case 'suggestions':
        this.router.navigate([routesCollection.MAIN_USER, routesCollection.USER_SUGGESTIONS], { replaceUrl: true });
        break;
      case 'messages':
        this.router.navigate([routesCollection.MAIN_USER, routesCollection.USER_MESSAGES], { replaceUrl: true });
        break;
      case 'profile':
        this.modalUpdateProfile.open();
        break;
      default:
        this.router.navigate([routesCollection.MAIN_USER, routesCollection.USER_BOOKS], { replaceUrl: true });
        break;
    }
  }
}
