import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-exercici1',
  templateUrl: './exercici1.component.html',
  styleUrl: './exercici1.component.css'
})
export class Exercici1Component {
  dada! : number;
  constructor(private formBuilder : FormBuilder) {}

  formulari : FormGroup = this.formBuilder.group({
    num1: [0, [Validators.min(1), Validators.max(1000), Validators.required]],
    num2: [0, [Validators.min(1), Validators.max(1000), Validators.required]]
  });

  Calcular() : void {
    this.dada = this.formulari.controls['num1'].value + this.formulari.controls['num2'].value;
  }
}
