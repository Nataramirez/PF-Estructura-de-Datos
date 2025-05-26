import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksAdminPanelComponent } from './books-admin-panel.component';

describe('BooksAdminPanelComponent', () => {
  let component: BooksAdminPanelComponent;
  let fixture: ComponentFixture<BooksAdminPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BooksAdminPanelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BooksAdminPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
