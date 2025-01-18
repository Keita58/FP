import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCartesJugadorComponent } from './get-cartes-jugador.component';

describe('GetCartesJugadorComponent', () => {
  let component: GetCartesJugadorComponent;
  let fixture: ComponentFixture<GetCartesJugadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetCartesJugadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCartesJugadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
