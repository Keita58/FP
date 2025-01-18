import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCartesBangSenseJugadorComponent } from './get-cartes-bang-sense-jugador.component';

describe('GetCartesBangSenseJugadorComponent', () => {
  let component: GetCartesBangSenseJugadorComponent;
  let fixture: ComponentFixture<GetCartesBangSenseJugadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetCartesBangSenseJugadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCartesBangSenseJugadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
