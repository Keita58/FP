import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgafarCartesComponent } from './agafar-cartes.component';

describe('AgafarCartesComponent', () => {
  let component: AgafarCartesComponent;
  let fixture: ComponentFixture<AgafarCartesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AgafarCartesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgafarCartesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
