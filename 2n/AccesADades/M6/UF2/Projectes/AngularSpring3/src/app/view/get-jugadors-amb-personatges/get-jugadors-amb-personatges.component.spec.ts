import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetJugadorsAmbPersonatgesComponent } from './get-jugadors-amb-personatges.component';

describe('GetJugadorsAmbPersonatgesComponent', () => {
  let component: GetJugadorsAmbPersonatgesComponent;
  let fixture: ComponentFixture<GetJugadorsAmbPersonatgesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetJugadorsAmbPersonatgesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetJugadorsAmbPersonatgesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
