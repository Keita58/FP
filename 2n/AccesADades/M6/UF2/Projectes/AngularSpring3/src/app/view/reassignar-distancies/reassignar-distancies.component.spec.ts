import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReassignarDistanciesComponent } from './reassignar-distancies.component';

describe('ReassignarDistanciesComponent', () => {
  let component: ReassignarDistanciesComponent;
  let fixture: ComponentFixture<ReassignarDistanciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReassignarDistanciesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReassignarDistanciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
