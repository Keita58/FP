import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetJugadorsRolsVidaComponent } from './get-jugadors-rols-vida.component';

describe('GetJugadorsRolsVidaComponent', () => {
  let component: GetJugadorsRolsVidaComponent;
  let fixture: ComponentFixture<GetJugadorsRolsVidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetJugadorsRolsVidaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetJugadorsRolsVidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
