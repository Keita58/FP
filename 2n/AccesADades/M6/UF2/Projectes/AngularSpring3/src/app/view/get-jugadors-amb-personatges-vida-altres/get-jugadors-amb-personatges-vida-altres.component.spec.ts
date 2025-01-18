import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetJugadorsAmbPersonatgesVidaAltresComponent } from './get-jugadors-amb-personatges-vida-altres.component';

describe('GetJugadorsAmbPersonatgesVidaAltresComponent', () => {
  let component: GetJugadorsAmbPersonatgesVidaAltresComponent;
  let fixture: ComponentFixture<GetJugadorsAmbPersonatgesVidaAltresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetJugadorsAmbPersonatgesVidaAltresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetJugadorsAmbPersonatgesVidaAltresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
