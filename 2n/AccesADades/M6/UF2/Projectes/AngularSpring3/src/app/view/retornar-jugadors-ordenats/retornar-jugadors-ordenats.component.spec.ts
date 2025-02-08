import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetornarJugadorsOrdenatsComponent } from './retornar-jugadors-ordenats.component';

describe('RetornarJugadorsOrdenatsComponent', () => {
  let component: RetornarJugadorsOrdenatsComponent;
  let fixture: ComponentFixture<RetornarJugadorsOrdenatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RetornarJugadorsOrdenatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RetornarJugadorsOrdenatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
