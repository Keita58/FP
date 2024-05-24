import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Exercici1Component } from './exercici1.component';
import {ReactiveFormsModule} from "@angular/forms";

describe('Exercici1Component', () => {
  let component: Exercici1Component;
  let fixture: ComponentFixture<Exercici1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Exercici1Component],
      imports:[ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Exercici1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Prova Comprova número', () => {
    component.formulari.controls['num1'].setValue(35);
    fixture.detectChanges();
    expect(component.formulari.controls['num1'].value).toBe(35);
  });

  it('Prova Comprova funció', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    expect(component.dada).toBe(57);
  });

  it('Prova Element del Dom', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toBe("El resultat és: 57");
  });
});
