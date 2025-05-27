import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BinaryTreeVisualizerComponent } from './binary-tree-visualizer.component';

describe('BinaryTreeVisualizerComponent', () => {
  let component: BinaryTreeVisualizerComponent;
  let fixture: ComponentFixture<BinaryTreeVisualizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BinaryTreeVisualizerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BinaryTreeVisualizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
