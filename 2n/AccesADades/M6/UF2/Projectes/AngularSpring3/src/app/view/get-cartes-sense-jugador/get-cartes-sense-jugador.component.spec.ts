import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCartesSenseJugadorComponent } from './get-cartes-sense-jugador.component';

describe('GetCartesSenseJugadorComponent', () => {
  let component: GetCartesSenseJugadorComponent;
  let fixture: ComponentFixture<GetCartesSenseJugadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetCartesSenseJugadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCartesSenseJugadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
