import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Alive2Component } from './alive2.component';

describe('Alive2Component', () => {
  let component: Alive2Component;
  let fixture: ComponentFixture<Alive2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Alive2Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Alive2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
