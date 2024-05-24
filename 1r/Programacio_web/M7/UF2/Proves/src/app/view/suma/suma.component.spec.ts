import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SumaComponent } from './suma.component';
import {ReactiveFormsModule} from "@angular/forms";

describe('SumaComponent', () => {
  let component: SumaComponent;
  let fixture: ComponentFixture<SumaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SumaComponent],
      imports: [ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SumaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Prova Comprova número - Suma', () => {
    component.formulari.controls['num1'].setValue(35);
    fixture.detectChanges();
    expect(component.formulari.controls['num1'].value).toBe(35);
  });

  it('Prova Comprova funció - Suma', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    expect(component.resultat).toBe("La teva suma és: 57");
  });

  it('Prova Element del Dom - Suma', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toBe("La teva suma és: 57");
  });
});
