import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCartesFallasteJugadorComponent } from './get-cartes-fallaste-jugador.component';

describe('GetCartesFallasteJugadorComponent', () => {
  let component: GetCartesFallasteJugadorComponent;
  let fixture: ComponentFixture<GetCartesFallasteJugadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetCartesFallasteJugadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCartesFallasteJugadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
