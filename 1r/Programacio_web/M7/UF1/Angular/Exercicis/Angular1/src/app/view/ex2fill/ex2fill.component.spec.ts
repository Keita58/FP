import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ex2fillComponent } from './ex2fill.component';

describe('Ex2fillComponent', () => {
  let component: Ex2fillComponent;
  let fixture: ComponentFixture<Ex2fillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Ex2fillComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Ex2fillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
