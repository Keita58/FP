import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComptarComponent } from './comptar.component';
import {ReactiveFormsModule} from "@angular/forms";

describe('ComptarComponent', () => {
  let component: ComptarComponent;
  let fixture: ComponentFixture<ComptarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComptarComponent],
      imports:[ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComptarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Prova Comprova text - Comptar', () => {
    component.formulari.controls['frase'].setValue("Holis");
    fixture.detectChanges();
    expect(component.formulari.controls['frase'].value).toBe("Holis");
  });

  it('Prova Comprova funció - Comptar', () => {
    component.formulari.controls['frase'].setValue("Holis");
    component.Calcular();
    fixture.detectChanges();
    expect(component.res).toBe("En el text anterior hi havia 0 'a', 0 'e', 1 'i', 1 'o' i 0 'u'.");
  });

  it('Prova Element del Dom -  Comptar', () => {
    component.formulari.controls['frase'].setValue("Holis");
    component.Calcular();
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toBe("En el text anterior hi havia 0 'a', 0 'e', 1 'i', 1 'o' i 0 'u'.");
  });
});
