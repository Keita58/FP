import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalPartidaComponent } from './final-partida.component';

describe('FinalPartidaComponent', () => {
  let component: FinalPartidaComponent;
  let fixture: ComponentFixture<FinalPartidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FinalPartidaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FinalPartidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
