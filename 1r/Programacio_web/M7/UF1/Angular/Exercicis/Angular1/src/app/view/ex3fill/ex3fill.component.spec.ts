import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ex3fillComponent } from './ex3fill.component';

describe('Ex3fillComponent', () => {
  let component: Ex3fillComponent;
  let fixture: ComponentFixture<Ex3fillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Ex3fillComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Ex3fillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
