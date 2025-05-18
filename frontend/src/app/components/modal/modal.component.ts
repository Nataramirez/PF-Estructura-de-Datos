import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal',
  standalone: true, // Angular 18 usa componentes standalone por defecto
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css',
  imports: [CommonModule]
})
export class ModalComponent {
  @Input() title: string = 'Modal';
  @Output() closed = new EventEmitter<void>();
  isVisible: boolean = false;

  open() {
    this.isVisible = true;
  }

  close() {
    this.isVisible = false;
    this.closed.emit();
  }
}
