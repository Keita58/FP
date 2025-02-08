import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeixarCartesComponent } from './deixar-cartes.component';

describe('DeixarCartesComponent', () => {
  let component: DeixarCartesComponent;
  let fixture: ComponentFixture<DeixarCartesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeixarCartesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeixarCartesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
