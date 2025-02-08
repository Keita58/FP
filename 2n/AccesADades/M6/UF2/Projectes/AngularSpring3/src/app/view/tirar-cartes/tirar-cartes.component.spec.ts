import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TirarCartesComponent } from './tirar-cartes.component';

describe('TirarCartesComponent', () => {
  let component: TirarCartesComponent;
  let fixture: ComponentFixture<TirarCartesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TirarCartesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TirarCartesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
