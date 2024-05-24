import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-triangle',
  templateUrl: './triangle.component.html',
  styleUrl: './triangle.component.css'
})
export class TriangleComponent {
  resultat! : number;

  constructor(private formBuilder : FormBuilder) {}

  formulari : FormGroup = this.formBuilder.group({
    base: [0, [Validators.min(1), Validators.max(1000), Validators.required]],
    alcada: [0, [Validators.min(1), Validators.max(1000), Validators.required]]
  });

  Calcular() : void {
    this.resultat = (this.formulari.controls['base'].value * this.formulari.controls['alcada'].value)/2;
  }
}
