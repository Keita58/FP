import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPartidesIsFinalitzadaComponent } from './get-partides-is-finalitzada.component';

describe('GetPartidesIsFinalitzadaComponent', () => {
  let component: GetPartidesIsFinalitzadaComponent;
  let fixture: ComponentFixture<GetPartidesIsFinalitzadaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetPartidesIsFinalitzadaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetPartidesIsFinalitzadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
