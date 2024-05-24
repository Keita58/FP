import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TriangleComponent } from './triangle.component';
import {ReactiveFormsModule} from "@angular/forms";

describe('TriangleComponent', () => {
  let component: TriangleComponent;
  let fixture: ComponentFixture<TriangleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TriangleComponent],
      imports:[ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TriangleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Prova Comprova número - Triangle', () => {
    component.formulari.controls['base'].setValue(35);
    fixture.detectChanges();
    expect(component.formulari.controls['base'].value).toBe(35);
  });

  it('Prova Comprova funció - Triangle', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    expect(component.resultat).toBe(385);
  });

  it('Prova Element del Dom - Triangle', () => {
    component.formulari.controls['num1'].setValue(35);
    component.formulari.controls['num2'].setValue(22);
    component.Calcular();
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toBe("El resultat és: 385");
  });
});
